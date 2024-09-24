package com.example.demo.Web;


import com.example.demo.Repositories.SubscriptionInterface;
import com.example.demo.Services.MembersService;
import com.example.demo.Services.SubscriptionService;
import com.example.demo.SubscriptionRequestDTO;
import com.example.demo.entities.Members;
import com.example.demo.entities.Subscriptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/subcriptions")
public class SubscriptionController  {

    @Autowired
    SubscriptionInterface si;

    @Autowired
    MembersService memberService;
    @Autowired
    SubscriptionService ss ;

    @GetMapping
    public ResponseEntity<List<Subscriptions>> Showallsubscriptions(){

                List<Subscriptions> subscriptions = si.findAll();
                return ResponseEntity.ok(subscriptions);

    }

    @PostMapping
    public ResponseEntity<Subscriptions> addSubscription(@RequestBody SubscriptionRequestDTO subscriptionRequest) {
        Members member = memberService.findById(subscriptionRequest.getMemberId());
        if (member == null) {
            return ResponseEntity.badRequest().build();
        }

        Subscriptions subscription = new Subscriptions();
        subscription.setMembers(member);
        subscription.setStartDate(subscriptionRequest.getStartDate());
        subscription.setEndDate(subscriptionRequest.getEndDate());
        subscription.setStatus(subscriptionRequest.getStatus());
        subscription.setType(subscriptionRequest.getType());

        Subscriptions savedSubscription = ss.save(subscription);
        return ResponseEntity.ok(savedSubscription);
    }

}
