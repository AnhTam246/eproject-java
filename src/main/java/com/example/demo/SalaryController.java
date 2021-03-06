/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import com.example.demo.entities.Contract;
import com.example.demo.entities.Salary;
import com.example.demo.entities.SalaryDetail;
import com.example.demo.entities.TimeWorking;
import com.example.demo.helpers.ResponseHandler;
import com.example.demo.services.ContractService;
import com.example.demo.services.SalaryDetailService;
import com.example.demo.services.SalaryService;
import com.example.demo.services.SpecialDateService;
import com.example.demo.services.StaffService;
import com.example.demo.services.TimeLeaveService;
import com.example.demo.services.TimeSpecialService;
import com.google.gson.Gson;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.example.demo.entities.Staff;
import com.example.demo.services.LeaveOtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tonamson
 */
@RestController
@RequestMapping("/salary")
public class SalaryController {

    @Autowired
    private TimeLeaveService service;

    @Autowired
    private TimeSpecialService timeSpecialService;

    @Autowired
    private LeaveOtherService leaveOtherService;

    @Autowired
    private SalaryService salaryService;

    @Autowired
    private SalaryDetailService salaryDetailService;

    @Autowired
    private ContractService contractService;

    @Autowired
    private StaffService staffService;

    @Autowired
    private SpecialDateService specialService;

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat dateSqlFormat = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm:ss");
    List<Integer> paidLeaveId = new ArrayList();

    public SalaryController() {
        // ngh??? ph??p c?? c??ng
        this.paidLeaveId.add(1);
        this.paidLeaveId.add(6);
        this.paidLeaveId.add(7);
    }

    @GetMapping(path = "/list")
    public ResponseEntity<Object> listSalary() {
        List<Salary> data = salaryService.findAll();
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "G???i y??u c???u th??nh c??ng", data);
    }

    @GetMapping(path = "/details")
    public ResponseEntity<Object> salaryDetails(@RequestParam int id) {
        List<SalaryDetail> data = salaryDetailService.findBySalaryId(id);
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "G???i y??u c???u th??nh c??ng", data);
    }

    @GetMapping(path = "/detail")
    public ResponseEntity<Object> salaryDetail(@RequestParam int id) {
        SalaryDetail data = salaryDetailService.findById(id);
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "G???i y??u c???u th??nh c??ng", data);
    }

    @GetMapping(path = "/find-salary")
    public ResponseEntity<Object> findSalary(@RequestParam int id) {
        Salary data = salaryService.findById(id);
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "G???i y??u c???u th??nh c??ng", data);
    }

    @GetMapping(path = "/find-salary-by-staff")
    public ResponseEntity<Object> findSalaryBySaff(@RequestParam int staff_id) {
        List<SalaryDetail> data = salaryDetailService.findByStaffId(staff_id);
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "G???i y??u c???u th??nh c??ng", data);
    }

    @GetMapping(path = "/delete")
    public ResponseEntity<Object> deleteSalary(@RequestParam int id) {
        boolean result = salaryService.deleteSalaryById(id);
        if (result) {
            boolean resultDeleteChild = salaryDetailService.deleteBySalaryId(id);
            if (resultDeleteChild) {
                return ResponseHandler.generateResponse(HttpStatus.OK, true, "X??a th??nh c??ng", result);
            }
            return ResponseHandler.generateResponse(HttpStatus.OK, false, "X??a chi ti???t t??nh l????ng th???t b???i", resultDeleteChild);
        } else {
            return ResponseHandler.generateResponse(HttpStatus.OK, false, "X??a th???t b???i", result);
        }
    }

    @PostMapping(path = "/update-status")
    public ResponseEntity<Object> updateStatusSalary(@RequestBody HashMap<String, String> body) {
        int id = Integer.parseInt(body.get("id").toString());
        String status = body.get("status").toString();
        boolean result = salaryService.updateStatus(id, status);
        if (result) {
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Thay ?????i tr???ng th??i th??nh c??ng", true);
        }
        return ResponseHandler.generateResponse(HttpStatus.OK, false, "Thay ?????i tr???ng th??i th???t b???i", false);
    }

    @PostMapping(path = "/calculated")
    public ResponseEntity<Object> calculatedSalary(@RequestBody Map<String, Object> body) {
        try {
            String from_date = body.get("from_date").toString();
            String to_date = body.get("to_date").toString();

            Date from = new SimpleDateFormat("yyyy-MM-dd").parse(from_date);
            Date to = new SimpleDateFormat("yyyy-MM-dd").parse(to_date);

            Date from_date_calculated = dateSqlFormat.parse(from_date);
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(from);
            int standard_days = this.calculateNormalDays(calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR));

            Salary salarySuccess = this.checkHaveFromDateAndToDateSuccess(from_date, to_date);
            if (salarySuccess != null) {
                return ResponseHandler.generateResponse(HttpStatus.OK, false, "???? kh??a b???ng l????ng th???i gian n??y kh??ng th??? t??nh l???i", null);
            }

            // Ki???m tra xem th???i gian from v?? to c?? b??? tr??ng v??o l???ch ???? kh??a tr?????c ???? kh??ng
            boolean checkHaveFromDate = this.checkCalculatedSalaryDate(from_date);
            boolean checkHaveToDate = this.checkCalculatedSalaryDate(to_date);

            if (checkHaveFromDate || checkHaveToDate) {
                return ResponseHandler.generateResponse(HttpStatus.OK, false, "Th???i gian b??? tr??ng v???i d??? li???u ???? kh??a l????ng tr?????c ????", null);
            }

            Salary salaryData = this.checkSimilarCalculatedSalaryDate(from_date, to_date);
            if (salaryData == null) {
                // n???u kh??ng b??? tr??ng th?? t???o m???i
                salaryData = salaryService.save(new Salary(dateSqlFormat.parse(from_date), dateSqlFormat.parse(to_date), standard_days)); // t???o data cho salary t??nh l????ng kho???ng th???i gian n??y
            }

            ArrayList listStaffIdCalculated = (ArrayList) body.get("staffs"); // mu???n t??nh c??c nh??n vi??n n??o trong danh s??ch

            // ch???m c??ng l??m b??nh th?????ng (done)
            // http://localhost:8888/time-leave/get-all-staff-time-from-to?from_date=2021-03-01&to_date=2021-03-30
            ArrayList<Map<String, Object>> timekeepings = service.getAllStaffTimeFromTo(from_date, to_date);
            HashMap<String, HashMap<String, Object>> staff_timekeeping = new HashMap(); // staff_id, timekeeping
            staff_timekeeping = this.calculateStaffSalary(from_date_calculated, staff_timekeeping, timekeepings, "check_in_day_y_m_d", listStaffIdCalculated, false);

            // l???y data ph???n ch???m c??ng b??? xung
            // v?? c?? tr?????ng h???p duy???t ch???m th?? l???y day_approved c???a ng??y duy???t ????? b??? xung c??ng
            // http://localhost:8888/time-leave/get-time-leave-from-to?from_date=2021-03-01&to_date=2021-03-30
            List<Map<String, Object>> timekeepings_forgot = salaryService.getAdditionalTimekeepingFromTo(from_date, to_date);
            staff_timekeeping = this.calculateStaffSalary(from_date_calculated, staff_timekeeping, timekeepings_forgot, "day_time_leave", listStaffIdCalculated, true);

            // ngh??? l??? ???????c t??nh c??ng
            ArrayList<Map<String, Object>> timekeeping_special_date = timeSpecialService.GetTimeSepcialFromTo(from, to);
            staff_timekeeping = this.calculateStaffSalary(from_date_calculated, staff_timekeeping, timekeeping_special_date, "day_time_special", listStaffIdCalculated, true);

            // c??c lo???i ph??p ???????c c???ng c??ng
            ArrayList<Map<String, Object>> leaveOtherDate = leaveOtherService.GetLeaveOtherFromTo(from_date, to_date);
            staff_timekeeping = this.calculateStaffSalary(from_date_calculated, staff_timekeeping, leaveOtherDate, "day_time_special", listStaffIdCalculated, true);

            for (Map.Entry<String, HashMap<String, Object>> entry : staff_timekeeping.entrySet()) {
                TimeWorking timeWorking = (TimeWorking) entry.getValue().get("timekeeping");
                if (timeWorking.getTotal_time() == 0) {
                    continue;
                }
                SalaryDetail salaryDetail = new SalaryDetail();
                salaryDetail.setStaffId(timeWorking.getStaff_id());
                salaryDetail.setSalaryId(salaryData.getId());
                salaryDetail.setSalary(timeWorking.getTotal_salary());
                salaryDetail.setSalaryOt(timeWorking.getTotal_salary_ot());

                // l???p details ????? t??nh coi t???ng bao nhi??u c??ng th?????ng v?? c??ng l???
                List<HashMap<String, Object>> details = timeWorking.getDetails();
                if (details != null && details.size() > 0) {
                    for (int i = 0; i < details.size(); i++) {
                        HashMap<String, Object> detail = details.get(i);
                        boolean paidLeave = Boolean.parseBoolean(detail.get("paid_leave").toString());
                        float total_working_of_day = Float.parseFloat(detail.get("total_working_of_day").toString());
                        int multiply_day = Integer.parseInt(detail.get("multiply_day").toString());

                        if (paidLeave) {
                            // n???u l?? paidLeave true ngh??a l?? ng??y ngh??? c?? l????ng
                            salaryDetail.setTotalSpecialDay(total_working_of_day);
                        } else {
                            // c??n l???i l?? ng??y l??m vi???c
                            if (multiply_day == 1) {
                                salaryDetail.setTotalDayWork(total_working_of_day);
                            }
                        }

                    }
                }

                Contract contract = this.detailContract(timeWorking.getStaff_id(), from_date_calculated);
                if (contract != null) {
                    salaryDetail.setBaseSalaryContract(contract.getBaseSalary());
                }
                HashMap<String, HashMap<String, Object>> dataOptions = (HashMap<String, HashMap<String, Object>>) body.get("options");
                List<HashMap<String, Object>> allowances = new ArrayList();
                List<HashMap<String, Object>> insurances = new ArrayList();
                List<HashMap<String, Object>> taxs = new ArrayList();
                for (Map.Entry<String, HashMap<String, Object>> detailOptions : dataOptions.entrySet()) {
                    HashMap<String, Object> optionMap = detailOptions.getValue();
                    double value = Double.parseDouble(optionMap.get("value").toString());
                    if (optionMap.get("type").toString().equals("ALLOWANCE")) {
                        // c???ng ti???n ph??? c???p
                        salaryDetail.setTotalAllowance(value);
                        allowances.add(optionMap);
                    } else if (optionMap.get("type").toString().equals("INSURANCE")) {
                        // c???ng ti???n b???o hi???m
                        if (salaryDetail.getBaseSalaryContract() > 0) {
                            // ??i???u ki???n l????ng c?? b???n ph???i > 0 th?? m???i t??nh ???????c % b???o hi???m
                            salaryDetail.setTotalInsurance(salaryDetail.getBaseSalaryContract() * value);
                        }
                        insurances.add(optionMap);
                    } else if (optionMap.get("type").toString().equals("TAX")) {
                        // c???ng ti???n thu???
                        salaryDetail.setTotalTax(value);
                        taxs.add(optionMap);
                    }
                }
                Gson gson = new Gson();
                salaryDetail.setAllowanceDetails(gson.toJson(allowances));
                salaryDetail.setInsuranceDetails(gson.toJson(insurances));
                salaryDetail.setTaxDetails(gson.toJson(taxs));
                salaryDetail.setDetails(gson.toJson(timeWorking.getDetails())); // ??p v??? json ????? l??u 
                double incomeTax = this.incomeTax(salaryDetail.getSalary(), salaryDetail.getSalaryOt(), allowances); // thu nh???p ch???u thu???
                salaryDetail.setIncomeTax(incomeTax); // thu nh???p ch???u thu???

                double taxableIncome = this.taxableIncome(incomeTax, salaryDetail.getTotalInsurance(), salaryDetail.getTotalTax()); // thu nh???p t??nh thu???
                salaryDetail.setTaxableIncome(taxableIncome); // thu nh???p t??nh thu???

                // thu??? thu nh???p c?? nh??n
                double personalTax = this.personalTax(taxableIncome);
                salaryDetail.setPersonalTax(personalTax);

                // th???c nh???n
                double salaryActually = this.salaryActuallyReceived(salaryDetail.getSalary(), salaryDetail.getSalaryOt(), salaryDetail.getTotalAllowance(), salaryDetail.getTotalInsurance(), personalTax);
                salaryDetail.setSalaryActuallyReceived(salaryActually);

                salaryDetailService.save(salaryDetail);
            }

            return ResponseHandler.generateResponse(HttpStatus.OK, true, "T??nh to??n ho??n t???t", staff_timekeeping);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.OK, false, e.getMessage(), null);
        }
    }

    private double incomeTax(double salaryBase, double salaryOT, List<HashMap<String, Object>> allowances) {
        // 6
        // thu nh???p ch???u thu???
        // L????ng CB + L????ng t??ng ca + Ph??? c???p (Kh??ng t??nh thu???) - Ph??? c???p (t??nh thu???)
        try {
            double result = salaryBase + salaryOT;
            if (result == 0) {
                return 0;
            }
            for (HashMap<String, Object> item : allowances) {
                double value = Double.parseDouble(item.get("value").toString());
                double min_tax = Double.parseDouble(item.get("min_tax").toString());
                boolean have_tax = Boolean.parseBoolean(item.get("have_tax").toString());
                if (have_tax) {
                    // c?? t??nh thu???
                    /**
                     * VD: c??m 25.000 ng??y / th??ng 550.000 th??ng 730.000 >
                     * 550.000 th?? kh??ng t??nh thu???
                     */
                    if (value > min_tax) { // c??m l???n h??n 730k th?? t??nh ph???n d??
                        result += value - min_tax; // c???ng ph???n d?? ph??? c???p b??? t??nh thu???
                    } else {
                        // n???u nh??? h??n th?? tr??? ph???n c??m ra
                        result -= value;
                    }
                } else {
                    // tr?????ng h???p kh??ng t??nh thu??? th?? tr??? ti???n ph??? c???p ???? ra
                    result -= value; // tr??? ph???n ph??? c???p kh??ng t??nh thu??? ra
                }
            }
            return result;
        } catch (Exception e) {
            return 0;
        }
    }

    private double taxableIncome(double incomeTax, double total_insurance, double total_tax) {
        // 7
        // thu nh???p t??nh thu???
        // thu nh???p ch???u thu??? - c??c kho???n kh???u tr???
        try {
            double result = incomeTax - total_insurance - total_tax;
            return result > 0 ? result : 0;
        } catch (Exception e) {
            return 0;
        }
    }

    private double personalTax(double taxableIncome) {
        // t??nh thu??? thu nh???p c?? nh??n
        if (taxableIncome <= 0) {
            return 0;
        }

        if (taxableIncome <= 5000000) {
            // d?????i 5tr
            // 5% * TNTT
            return ((float) 5 / 100) * taxableIncome;
        } else if (5000000 < taxableIncome && taxableIncome <= 10000000) {
            // 5tr < TNTT <= 10tr
            // 10% * TNTT - 0.25 * 10^6
            return (((float) 10 / 100) * taxableIncome) - (0.25 * Math.pow(10, 6));
        } else if (10000000 < taxableIncome && taxableIncome <= 18000000) {
            // 10tr < TNTT <= 18tr
            // 15% * TNTT - 0.75 * 10^6
            return (((float) 15 / 100) * taxableIncome) - (0.75 * Math.pow(10, 6));
        } else if (18000000 < taxableIncome && taxableIncome <= 32000000) {
            // 18tr < TNTT <= 32tr
            // 20% * TNTT - 1.65 * 10^6
            return (((float) 20 / 100) * taxableIncome) - (1.65 * Math.pow(10, 6));
        } else if (32000000 < taxableIncome && taxableIncome <= 52000000) {
            // 32tr < TNTT <= 52tr
            // 25% * TNTT - 3.25 * 10^6
            return (((float) 25 / 100) * taxableIncome) - (3.25 * Math.pow(10, 6));
        } else if (52000000 < taxableIncome && taxableIncome <= 80000000) {
            // 52tr < TNTT <= 80tr
            // 30% * TNTT - 5.85 * 10^6
            return (((float) 30 / 100) * taxableIncome) - (5.85 * Math.pow(10, 6));
        } else {
            // > 80tr
            // 35% * TNTT - 9.85 * 10^6
            return (((float) 35 / 100) * taxableIncome) - (9.85 * Math.pow(10, 6));
        }
    }

    private double salaryActuallyReceived(double salary, double salary_ot, double total_allowance, double total_insurance, double personal_tax) {
        return salary + salary_ot + total_allowance - total_insurance - personal_tax;
    }

    private HashMap<String, HashMap<String, Object>> calculateStaffSalary(Date date, HashMap<String, HashMap<String, Object>> staff_timekeeping, List<Map<String, Object>> timekeepings, String day_colume, ArrayList listStaffIdCalculated, boolean isSpecialDate) {
        // isSpecialDate true: m???c ?????nh l?? ng??y n??y to??n b??? l?? ngh??? l??? c?? c??ng
        // tr?????ng h???p isSpecialDate false: th?? ph???i check coi c??c lo???i ph??p n???u n???m trong ???? coi nh?? ngh??? c?? c??ng
        try {
            Calendar calendar = new GregorianCalendar();
            // l???y data ph???n ch???m c??ng
            for (Map<String, Object> item : timekeepings) {
                String staff_id = item.get("staff_id").toString();
                if (!listStaffIdCalculated.contains(staff_id)) {
                    continue;
                }
                int staff_id_int = Integer.parseInt(staff_id);

                // c???ng d???n to??n b??? time l???i cho ra s??? ph??t th??
                if (!staff_timekeeping.containsKey(staff_id)) {
                    HashMap<String, Object> timekeeping = new HashMap();
                    TimeWorking timeWorking = new TimeWorking();
                    Staff staff = staffService.findOne(staff_id_int);
                    List<HashMap<String, Object>> details = new ArrayList();
                    timeWorking.setStaff_id(staff_id_int);
                    timeWorking.setStaff_name(staff.getFirstname() + ' ' + staff.getLastname());
                    timeWorking.setTotal_time(0); // m???c ?????nh t???ng c??ng ban ?????u l?? 0
                    timeWorking.setTotal_salary(0); // set m???c ?????nh l????ng kh???i ?????u l?? 0
                    timeWorking.setTotal_salary_ot(0); // set m???c ?????nh l????ng kh???i ?????u l?? 0
                    float total_working_of_day = Float.parseFloat(item.get("number_time").toString()); // c??ng l??m vi???c 1.0 ho???c 0.5

                    if (total_working_of_day > 0) {
                        // ch??a t???n t???i th?? add th??m
                        String time_str = item.get(day_colume).toString();
                        Date time = dateSqlFormat.parse(time_str);

                        calendar.setTime(date);

                        // t??nh l????ng
                        double salary_per_day = this.calculateSalaryPerDay(staff_id_int, date);
                        if (salary_per_day > 0) { // salary > 0 ngh??a l?? t??m th???y h???p ?????ng th?? m???i t??nh to??n
                            // n???u c?? t???o h???p ?????ng r???i th?? m???i t??nh l????ng kh??ng th?? kh??ng add d??? li???u ch???m c??ng v??o
                            timeWorking.setTotal_time(total_working_of_day);

                            double salary_by_one_hour = salary_per_day / 8; // quy ra m???i gi??? bao nhi??u ti???n
                            int multiply_day = Integer.parseInt(item.get("multiply").toString()); // l???y ng??y ch???m c??ng n??y x bao nhi??u
                            float ot_hours = getHourTime(item.get("ot"));
                            double salary_of_ot_150 = multiply_day == 1 ? salary_by_one_hour * ot_hours * 1.5 : 0;
                            double salary_of_ot_200 = multiply_day == 2 ? salary_by_one_hour * ot_hours * 2 : 0;
                            double salary_of_ot_300 = multiply_day == 3 ? salary_by_one_hour * ot_hours * 3 : 0;
                            double salary_of_day = multiply_day > 0 ? (salary_by_one_hour * (8 * total_working_of_day)) : 0; // (l????ng ng??y h??m nay = l????ng 1h * t???ng gi??? l??m)

                            if (multiply_day <= 0) {
                                // tr?????ng h???p n???u <= 0 ngh??a l?? kh??ng t??nh l????ng
                                continue;
                            }
                            // ki???m tra n???u l?? ng??y l??? hay ng??y ngh??? th?? x l????ng ng??y ????
                            if (multiply_day == 2) {
                                float total_ot_time = 8 * total_working_of_day;
                                ot_hours += total_ot_time;
                                salary_of_day = 0;
                                salary_of_ot_200 += (total_ot_time * salary_by_one_hour) * 2;
                            } else if (multiply_day == 3) {
                                float total_ot_time = 8 * total_working_of_day;
                                ot_hours += total_ot_time;
                                salary_of_day = 0;
                                salary_of_ot_300 += (total_ot_time * salary_by_one_hour) * 3;
                            }
                            timeWorking.setTotal_salary(salary_of_day + timeWorking.getTotal_salary());
                            timeWorking.setTotal_salary_ot(salary_of_ot_150 + salary_of_ot_200 + salary_of_ot_300 + timeWorking.getTotal_salary_ot());

                            // add chi ti???t t??nh l????ng nh?? th??? n??o d???a v??o h???p ?????ng n??o v?? s??? l????ng c???a ng??y ???? d???a v??o h???p ?????ng
                            HashMap<String, Object> detail = new HashMap();
                            if (isSpecialDate) {
                                detail.put("paid_leave", true);
                            } else {
                                int type = Integer.parseInt(item.get("type").toString());
                                detail.put("paid_leave", paidLeaveId.contains(type) ? true : false);
                            }
                            detail.put("salary_per_day", salary_per_day);
                            detail.put("ot_hours", ot_hours); // th???i gian OT th??m
                            detail.put("salary_by_one_hour", salary_by_one_hour);
                            detail.put("multiply_day", multiply_day);
                            detail.put("total_working_of_day", total_working_of_day);
                            detail.put("salary_of_ot_150", salary_of_ot_150);
                            detail.put("salary_of_ot_200", salary_of_ot_200);
                            detail.put("salary_of_ot_300", salary_of_ot_300);
                            detail.put("total_salary", salary_of_day);
                            detail.put("type_note", item.get("type_note").toString());
                            detail.put("total_salary_ot", salary_of_ot_150 + salary_of_ot_200 + salary_of_ot_300);
                            detail.put("day_detail", time_str);
                            detail.put("standard_days", this.calculateNormalDays(calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR)));
                            detail.put("contract", this.detailContract(staff_id_int, time));
                            details.add(detail);
                            timeWorking.setDetails(details);
                        }
                    }
                    timekeeping.put("timekeeping", timeWorking);
                    staff_timekeeping.put(item.get("staff_id").toString(), timekeeping);
                } else {
                    // n???u ???? t???n t???i r???i th?? b???t ?????u c???ng th??m s??? ph??t
                    HashMap<String, Object> detailTimekeeping = staff_timekeeping.get(staff_id); // l???y timekeeping
                    TimeWorking timeWorking = (TimeWorking) detailTimekeeping.get("timekeeping"); // l???y object ch???m c??ng trong hashmap
                    List<HashMap<String, Object>> details = timeWorking.getDetails();
                    float total_working_of_day = Float.parseFloat(item.get("number_time").toString()); // c??ng l??m vi???c 1.0 ho???c 0.5

                    if (total_working_of_day > 0) {
                        String time_str = item.get(day_colume).toString() + " " + item.get("time").toString();
                        Date time = simpleDateFormat.parse(time_str);
                        calendar.setTime(date);

                        // t??nh l????ng
                        double salary_per_day = this.calculateSalaryPerDay(staff_id_int, date);
                        if (salary_per_day > 0) {// salary > 0 ngh??a l?? t??m th???y h???p ?????ng th?? m???i t??nh to??n
                            float new_time = timeWorking.getTotal_time() + total_working_of_day;
                            timeWorking.setTotal_time(new_time);

                            double salary_by_one_hour = salary_per_day / 8; // quy ra m???i gi??? bao nhi??u ti???n
                            int multiply_day = Integer.parseInt(item.get("multiply").toString()); // l???y ng??y ch???m c??ng n??y x bao nhi??u

                            float ot_hours = getHourTime(item.get("ot"));
                            double salary_of_ot_150 = multiply_day == 1 ? salary_by_one_hour * ot_hours * 1.5 : 0;
                            double salary_of_ot_200 = multiply_day == 2 ? salary_by_one_hour * ot_hours * 2 : 0;
                            double salary_of_ot_300 = multiply_day == 3 ? salary_by_one_hour * ot_hours * 3 : 0;
                            double salary_of_day = multiply_day > 0 ? (salary_by_one_hour * (8 * total_working_of_day)) : 0;
                            if (multiply_day <= 0) {
                                // tr?????ng h???p n???u <= 0 ngh??a l?? kh??ng t??nh l????ng
                                continue;
                            }
                            // ki???m tra n???u l?? ng??y l??? hay ng??y ngh??? th?? x l????ng ng??y ????
                            if (multiply_day == 2) {
                                float total_ot_time = 8 * total_working_of_day;
                                ot_hours += total_ot_time;
                                salary_of_day = 0;
                                salary_of_ot_200 += (total_ot_time * salary_by_one_hour) * 2;
                            } else if (multiply_day == 3) {
                                float total_ot_time = 8 * total_working_of_day;
                                ot_hours += total_ot_time;
                                salary_of_day = 0;
                                salary_of_ot_300 += (total_ot_time * salary_by_one_hour) * 3;
                            }

                            timeWorking.setTotal_salary(salary_of_day + timeWorking.getTotal_salary());
                            timeWorking.setTotal_salary_ot(salary_of_ot_150 + salary_of_ot_200 + salary_of_ot_300 + timeWorking.getTotal_salary_ot());

                            // add chi ti???t t??nh l????ng nh?? th??? n??o d???a v??o h???p ?????ng n??o v?? s??? l????ng c???a ng??y ???? d???a v??o h???p ?????ng
                            HashMap<String, Object> detail = new HashMap();
                            if (isSpecialDate) {
                                detail.put("paid_leave", true);
                            } else {
                                int type = Integer.parseInt(item.get("type").toString());
                                detail.put("paid_leave", paidLeaveId.contains(type) ? true : false);
                            }
                            detail.put("salary_per_day", salary_per_day);
                            detail.put("ot_hours", ot_hours);
                            detail.put("salary_by_one_hour", salary_by_one_hour);
                            detail.put("multiply_day", multiply_day);
                            detail.put("total_working_of_day", total_working_of_day);
                            detail.put("salary_of_ot_150", salary_of_ot_150);
                            detail.put("salary_of_ot_200", salary_of_ot_200);
                            detail.put("salary_of_ot_300", salary_of_ot_300);
                            detail.put("total_salary", salary_of_day);
                            detail.put("type_note", item.get("type_note").toString());
                            detail.put("total_salary_ot", salary_of_ot_150 + salary_of_ot_200 + salary_of_ot_300);
                            detail.put("day_detail", time_str);
                            detail.put("standard_days", this.calculateNormalDays(calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR)));
                            detail.put("contract", this.detailContract(staff_id_int, time));
                            details.add(detail);
                            timeWorking.setDetails(details);
                        }

                        detailTimekeeping.put("timekeeping", timeWorking);
                        staff_timekeeping.put(staff_id, detailTimekeeping);
                    }
                }
            }
            return staff_timekeeping;
        } catch (Exception e) {
            return staff_timekeeping;
        }
    }

    // t??nh ti???n c??ng 1 ng??y l??m vi???c ra bao nhi??u
    private double calculateSalaryPerDay(int staff_id, Date date) {
        try {
            // truy???n th??ng v??o ????? bi???t th??ng ???? bao nhi??u c??ng
            // truy???n staff_id ????? l???y h???p ?????ng c???a nh??n vi??n ????
            Contract contract = contractService.findOneByStaffId(staff_id, dateSqlFormat.format(date).toString());
            if (contract == null) {
                return 0; // salary m???i ng??y s??? l?? 0 n???u kh??ng t??m th???y contract
            }

            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);

            int totalDays = calculateNormalDays(calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR)); // t??nh ra s??? c??ng chu???n c???a th??ng n??y
            return contract.getBaseSalary() / totalDays; // l????ng / t???ng c??ng ng??y
        } catch (Exception e) {
            return 0;
        }
    }

    private Contract detailContract(int staff_id, Date date) {
        return contractService.findOneByStaffId(staff_id, dateSqlFormat.format(date).toString());
    }

    // ?????m c?? bao nhi??u ng??y th?????ng kh??ng ph???i th??? 7 v?? ch??? nh???t
    private int calculateNormalDays(int month, int year) {
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(year - 1900, month - 1, 1);
        calendar.setTime(date);
        int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int count = 0;
        for (int i = 1; i <= days; i++) {
            Date date_count = new Date(year - 1900, month - 1, i);
            calendar.setTime(date_count);
            int day = calendar.get(Calendar.DAY_OF_WEEK);
            if (day != Calendar.SATURDAY && day != Calendar.SUNDAY) {
                count++;
            }
        }
        return count;
    }

    private int getHourTime(Object object) {
        if (object == null) {
            return 0;
        }
        String time = object.toString();
        // l???y gi??? t??? string HH:mm:ss
        if (time.length() < 8) {
            return 0;
        }
        try {
            Date date = simpleTimeFormat.parse(time);
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            return calendar.get(Calendar.HOUR);
        } catch (ParseException ex) {
            return 0;
        }
    }

    // ki???m tra xem from_date v?? to_date t???o t??nh l????ng c?? b??? d??nh v??o ???? t??nh tr?????c ???? hay kh??ng
    private boolean checkCalculatedSalaryDate(String date) {
        try {
            return salaryService.checkDateInRange(dateSqlFormat.parse(date));
        } catch (Exception e) {
            return true;
        }
    }

    private Salary checkSimilarCalculatedSalaryDate(String fromDate, String toDate) {
        try {
            return salaryService.checkSimilarDate(dateSqlFormat.parse(fromDate), dateSqlFormat.parse(toDate));
        } catch (ParseException ex) {
            return null;
        }
    }

    private Salary checkHaveFromDateAndToDateSuccess(String fromDate, String toDate) {
        try {
            return salaryService.checkFromDateAndToDateSuccess(dateSqlFormat.parse(fromDate), dateSqlFormat.parse(toDate));
        } catch (ParseException ex) {
            return null;
        }
    }
}
