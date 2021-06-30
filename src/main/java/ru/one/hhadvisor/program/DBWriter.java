package ru.one.hhadvisor.program;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.one.hhadvisor.output.Vacancy;
import ru.one.hhadvisor.program.threads.ThreadSaver;
import java.util.List;


@Component
public class DBWriter {
    public static Vacancy vacancy;
    public static int writecounter = 0;

    @Autowired
    private ThreadSaver threadSaver;




    public DBWriter() {
    }
//    @Autowired
//    public ThreadSaverRepo threadSaverRepo;



    public static void toWrite(List<Vacancy> v){
        System.out.println("V " + v.size());
        ThreadSaver.vacancyListFoeDBSaver = v;
        System.out.println("TOTAL " + ThreadSaver.vacancyListFoeDBSaver.size());
        int i = ThreadSaver.vacancyListFoeDBSaver.size();
        for (int j = 0; j < ThreadSaver.vacancyListFoeDBSaver.size()-1; j++) {
            writecounter = j;
            ThreadSaver threadSaver = new ThreadSaver();/// вероятная ошибка.
            threadSaver.run();
        }
        writecounter = 0;
        ThreadSaver.threadCounter = 0;

    }


}
