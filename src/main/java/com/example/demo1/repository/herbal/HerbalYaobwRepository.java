package com.example.demo1.repository.herbal;

import com.example.demo1.model.herbal.HerbalYaobw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface HerbalYaobwRepository extends JpaRepository<HerbalYaobw,Integer> {
}
