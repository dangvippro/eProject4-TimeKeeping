/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.timekeeping.timekeeping.repositories;

import com.timekeeping.timekeeping.models.Account;
import com.timekeeping.timekeeping.models.AttendanceRecord;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord, Integer> {
    List<AttendanceRecord> findByAccount_AccountID(int accountID);
    List<AttendanceRecord> findByAccount(Account account);
}