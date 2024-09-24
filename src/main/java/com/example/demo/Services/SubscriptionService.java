package com.example.demo.Services;

import com.example.demo.Repositories.SubscriptionInterface;
import com.example.demo.entities.Subscriptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {

    @Autowired
    SubscriptionInterface Si ;


    public Subscriptions createsubscription (Subscriptions subscriptions){

         Subscriptions newsubscription = Si.save(subscriptions);
                return  newsubscription ;

    }


    public Subscriptions save(Subscriptions subscription) {
        return Si.save(subscription);
    }
}
