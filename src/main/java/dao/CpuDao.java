package dao;

import models.Cpu;

import java.util.List;

public interface CpuDao {

    void add(Cpu cpu);

    List<Cpu> getAll();

    Cpu findById(int id);

    void update(int id, String manufacturer, String series, String speed, int cores, double price);

    void deleteById(int id);

    void clearAll();
}
