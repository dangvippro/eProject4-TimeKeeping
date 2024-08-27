package com.timekeeping.timekeeping.services;

import com.timekeeping.timekeeping.models.Payroll;
import com.timekeeping.timekeeping.models.Account;
import com.timekeeping.timekeeping.models.AttendanceRecord;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PayrollService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Payroll createPayroll(Payroll payroll) {
        payroll.calculateNetSalary();
        entityManager.persist(payroll);
        return payroll;
    }
    public List<Payroll> findByName(String name) {
        return entityManager.createQuery("FROM Payroll WHERE account.fullName LIKE :name", Payroll.class)
                .setParameter("name", "%" + name + "%")
                .getResultList();
    }
    public List<AttendanceRecord> findAttendancesByAccountAndDate(Account account, LocalDate date) {
        return entityManager.createQuery(
                        "SELECT a FROM AttendanceRecord a WHERE a.account = :account AND a.Date = :date", AttendanceRecord.class)
                .setParameter("account", account)
                .setParameter("date", date)
                .getResultList();
    }
    @Transactional
    public Payroll updatePayroll(Payroll payroll) {
        payroll.calculateNetSalary();
        entityManager.merge(payroll);
        return payroll;
    }

    public Payroll findPayrollById(int payrollID) {
        return entityManager.find(Payroll.class, payrollID);
    }

    @Transactional
    public void deletePayroll(int payrollID) {
        Payroll payroll = findPayrollById(payrollID);
        if (payroll != null) {
            entityManager.remove(payroll);
        }
    }

    public List<Payroll> findAllPayrolls() {
        return entityManager.createQuery("SELECT p FROM Payroll p", Payroll.class).getResultList();
    }
    public List<Account> getAllAccounts() {
        return entityManager.createQuery("SELECT a FROM Account a", Account.class).getResultList();
    }

    @Transactional
    public void generatePayroll(Account account, List<AttendanceRecord> attendances) {
        double totalWorkHours = attendances.stream().mapToDouble(AttendanceRecord::calculateWorkHours).sum();
        double grossSalary = account.getSalaryTemplate().getBaseSalary() * totalWorkHours;

        Payroll payroll = new Payroll();
        payroll.setAccount(account);
        payroll.setSalaryTemplate(account.getSalaryTemplate());
        payroll.setGrossSalary(grossSalary);

        payroll.calculateNetSalary();

        entityManager.persist(payroll);
    }

    public void calculateNetSalary(Payroll payroll) {
        double grossSalary = payroll.getGrossSalary();
        double bhxh = grossSalary * 0.08;
        double bhyt = grossSalary * 0.015;
        double bhtn = grossSalary * 0.01;
        double totalInsurance = bhxh + bhyt + bhtn;

        double taxableIncome = grossSalary - totalInsurance;
        double pit = calculatePIT(taxableIncome);

        payroll.setNetSalary(grossSalary - totalInsurance - pit);
    }

    private double calculatePIT(double taxableIncome) {
        double pit = 0;
        if (taxableIncome <= 5000000) {
            pit = taxableIncome * 0.05;
        } else if (taxableIncome <= 10000000) {
            pit = 5000000 * 0.05 + (taxableIncome - 5000000) * 0.1;
        } else if (taxableIncome <= 18000000) {
            pit = 5000000 * 0.05 + 5000000 * 0.1 + (taxableIncome - 10000000) * 0.15;
        } else if (taxableIncome <= 32000000) {
            pit = 5000000 * 0.05 + 5000000 * 0.1 + 8000000 * 0.15 + (taxableIncome - 18000000) * 0.2;
        } else if (taxableIncome <= 52000000) {
            pit = 5000000 * 0.05 + 5000000 * 0.1 + 8000000 * 0.15 + 14000000 * 0.2 + (taxableIncome - 32000000) * 0.25;
        } else if (taxableIncome <= 80000000) {
            pit = 5000000 * 0.05 + 5000000 * 0.1 + 8000000 * 0.15 + 14000000 * 0.2 + 20000000 * 0.25 + (taxableIncome - 52000000) * 0.3;
        } else {
            pit = 5000000 * 0.05 + 5000000 * 0.1 + 8000000 * 0.15 + 14000000 * 0.2 + 20000000 * 0.25 + 28000000 * 0.3 + (taxableIncome - 80000000) * 0.35;
        }
        return pit;
    }
}
