package com.uam.caronex.repository;

import com.uam.caronex.dto.RideResponse;
import com.uam.caronex.entity.RideEntity;
import com.uam.caronex.model.NewRideModel;
import com.uam.caronex.model.RideModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@RequiredArgsConstructor
@Repository
public class RideRepository {

    private  final MongoTemplate mongoTemplate;
    private final MongoOperations mongoOperations;


    public List<RideEntity> getRides(RideModel rideModel) {
        Query query = new Query();
        Distance radius = new Distance(3, Metrics.KILOMETERS);

        if (rideModel.getCpf() != null) {
            query.addCriteria(Criteria.where("owner.cpf").ne(rideModel.getCpf()));
        }

        if (rideModel.getOrigin() != null) {
            Point origin = new Point(rideModel.getOrigin()[0], rideModel.getOrigin()[1]);
            Circle originArea = new Circle(origin, radius);
            query.addCriteria(Criteria.where("origin.coordinates").withinSphere(originArea));
        }

        if (rideModel.getDestination() != null) {
            Point destination = new Point(rideModel.getDestination()[0], rideModel.getDestination()[1]);
            Circle destinationArea = new Circle(destination, radius);
            query.addCriteria(Criteria.where("destination.coordinates").withinSphere(destinationArea));
        }

        if ((rideModel.getStatus() != null)) {
            query.addCriteria(Criteria.where("status").is("AVAILABLE"));
        }

        query.with(Sort.by(Sort.Direction.DESC, "dateTime"));

        return mongoTemplate.find(query, RideEntity.class);
    }

    public List<RideEntity> getAllRidesAsDriver(String cpf) {
        Query query = new Query();
        query.addCriteria(Criteria.where("owner.cpf").is(cpf));
        query.with(Sort.by(Sort.Direction.DESC, "dateTime"));
        return mongoTemplate.find(query, RideEntity.class);
    }

    public List<RideEntity> getAllRidesAsUser(String cpf) {
        Query query = new Query();
        query.addCriteria(Criteria.where("owner.cpf").ne(cpf));
        query.with(Sort.by(Sort.Direction.DESC, "dateTime"));
        return mongoTemplate.find(query, RideEntity.class);
    }

    public RideEntity createRide(RideEntity model) {
        return mongoTemplate.save(model, "rides");
    }


}




