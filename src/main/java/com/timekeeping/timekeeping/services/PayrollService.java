package com.timekeeping.timekeeping.services;

import com.timekeeping.timekeeping.models.Payroll;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayrollService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Payroll createPayroll(Payroll payroll) {
        payroll.calculateNetSalary(); // Tính toán lương thực nhận trước khi lưu
        entityManager.persist(payroll);
        return payroll;
    }

    @Transactional
    public Payroll updatePayroll(Payroll payroll) {
        payroll.calculateNetSalary(); // Tính toán lại lương thực nhận nếu có thay đổi
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
}
