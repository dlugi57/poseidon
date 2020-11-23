package com.web.poseidon.service;


import com.web.poseidon.repositories.BidListRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Bid list data manipulation
 */
@Service
public class BidListImpl implements BidListService {

    static final Logger logger = LogManager
            .getLogger(BidListImpl.class);

    // initialize objects
    BidListRepository bidListRepository;

    /**
     * Field injection of bid list dao
     *
     * @param bidListRepository bid list dao
     */
    @Autowired
    public void setBankAccountDao(BidListRepository bidListRepository) {
        this.bidListRepository = bidListRepository;
    }
}
