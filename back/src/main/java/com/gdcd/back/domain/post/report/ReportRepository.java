package com.gdcd.back.domain.post.report;

import com.gdcd.back.domain.user.block.Block;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReportRepository extends MongoRepository<Report, Long>{
}
