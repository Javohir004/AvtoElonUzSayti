package uz.jvh.avtoelonuzsayti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.jvh.avtoelonuzsayti.repository.CarRepository;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;


}