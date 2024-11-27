package uz.jvh.avtoelonuzsayti.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.jvh.avtoelonuzsayti.domain.entity.Car;
import uz.jvh.avtoelonuzsayti.domain.entity.User;
import uz.jvh.avtoelonuzsayti.domain.enums.CarStatus;
import uz.jvh.avtoelonuzsayti.domain.request.CarRequest;
import uz.jvh.avtoelonuzsayti.domain.response.CarResponse;
import uz.jvh.avtoelonuzsayti.repository.CarRepository;
import uz.jvh.avtoelonuzsayti.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    public Car save(CarRequest carRequest) {
        Car car = mapToCar(carRequest);
        carRepository.save(car);
        return car;
    }

    public List<CarResponse> findCarByOwnerID(Long ownerID) {
        List<Car> byOwnerId = carRepository.findByOwnerIdAndIsActiveTrue(ownerID);
        return byOwnerId.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public CarResponse findCarById(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found"));
        return mapToResponse(car);
    }

    public CarResponse update(CarRequest carRequest , Long carId) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found"));

        car.setStatus(carRequest.getStatus());
        car.setBrand(carRequest.getBrand());
        car.setModel(carRequest.getModel());
        car.setRuns(carRequest.getRuns());
        car.setNotes(carRequest.getNotes());
        car.setPrice(carRequest.getPrice());
        car.setTransmission(carRequest.getTransmission());
        car.setHorsePower(carRequest.getHorsePower());
        car.setImagePaths(carRequest.getImagePaths());
        car.setEngineV(carRequest.getEngineV());
        car.setCreatedYear(carRequest.getCreatedYear());
        carRepository.save(car);
        return mapToResponse(car);
    }

    public void delete(Long id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new RuntimeException("Car not found"));
        car.setActive(false);
        carRepository.save(car);
    }

    public List<CarResponse> getAllCars() {
        List<Car> cars = carRepository.findCarsByStatusAndIsActiveTrue(CarStatus.AVAILABLE);
        return cars.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private CarResponse mapToResponse(Car car){
        CarResponse carResponse = new CarResponse();
        carResponse.setId(car.getId());
        carResponse.setBrand(car.getBrand());
        carResponse.setModel(car.getModel());
        carResponse.setCreatedYear(car.getCreatedYear());
        carResponse.setEngineV(car.getEngineV());
        carResponse.setHorsePower(car.getHorsePower());
        carResponse.setNotes(car.getNotes());
        carResponse.setRuns(car.getRuns());
        carResponse.setStatus(car.getStatus());
        carResponse.setTransmission(car.getTransmission());
        carResponse.setPrice(car.getPrice());
        carResponse.setImagePaths(car.getImagePaths());

        User owner = car.getOwner();
        carResponse.setOwnerId(owner.getId());
        carResponse.setOwnerName(owner.getUsername());
        carResponse.setOwnerEmail(owner.getEmail());
        carResponse.setOwnerPhone(owner.getPhoneNumber());
        carResponse.setOwnerAddress(owner.getAddress());
        return carResponse;
    }

    public CarResponse sellCar(Long oldOwnerId , Long newOwnerId , double price) {

        Car car = carRepository.findCarByOwnerIdAndPriceAndIsActiveTrue(oldOwnerId, price)
                .orElseThrow(() -> new RuntimeException("Car not found"));

        User newUser = userRepository.findById(newOwnerId).
                orElseThrow(() -> new RuntimeException("User not found"));
        newUser.setBalance(newUser.getBalance() + price);
        userRepository.save(newUser);


        User oldOwner = userRepository.findById(oldOwnerId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        oldOwner.setBalance(oldOwner.getBalance() - price);
        userRepository.save(oldOwner);

        car.setOwner(newUser);
        car.setStatus(CarStatus.SOLD);
        carRepository.save(car);
        return mapToResponse(car);
    }

    private Car mapToCar(CarRequest carRequest) {
        User byId = userRepository.findById(carRequest.getOwnerId())
                .orElseThrow(() -> new RuntimeException("User Not Found"));
        Car car = new Car();
        car.setBrand(carRequest.getBrand());
        car.setModel(carRequest.getModel());
        car.setImagePaths(carRequest.getImagePaths());
        car.setCreatedYear(carRequest.getCreatedYear());
        car.setEngineV(carRequest.getEngineV());
        car.setHorsePower(carRequest.getHorsePower());
        car.setNotes(carRequest.getNotes());
        car.setOwner(byId);
        car.setRuns(carRequest.getRuns());
        car.setStatus(carRequest.getStatus());
        car.setTransmission(carRequest.getTransmission());
        car.setPrice(carRequest.getPrice());
        return car;
    }


}
