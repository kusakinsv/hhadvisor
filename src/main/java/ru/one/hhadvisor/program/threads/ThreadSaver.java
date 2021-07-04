package ru.one.hhadvisor.program.threads;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;


import ru.one.hhadvisor.entity.repos.ThreadSaverRepo;
import ru.one.hhadvisor.output.Vacancy;
import ru.one.hhadvisor.program.DBWriter;

import java.util.ArrayList;
import java.util.List;
@Service
@Scope("prototype")
public class ThreadSaver extends Thread{
    public static List<Vacancy> vacancyListFoeDBSaver = new ArrayList<>();
    public static int threadCounter = 0;
    @Autowired
    public ThreadSaverRepo threadSaverRepo;

    @Override
    public void run() {
        System.out.println("Поток записи открыт " + getName());
        System.out.println(DBWriter.writecounter);
        System.out.println(vacancyListFoeDBSaver.get(DBWriter.writecounter).getName());
        threadSaverRepo.save(vacancyListFoeDBSaver.get(DBWriter.writecounter));
        threadCounter++;
        System.out.println("Поток завершен " +getName());
    }

}