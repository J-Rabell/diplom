package com.example.questionnaire_v3.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.questionnaire_v3.database.entity.Account;

import java.util.List;

@Dao
public interface AccountDao {

    @Insert
    void insert(Account account);

    @Query("SELECT TOP(1) FROM account")
    Account getOne();
    @Update
    void update(Account account);

    @Delete
    void delete(Account account);

}