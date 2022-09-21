package com.gdcd.back.service.sequence.user.block;

import com.gdcd.back.domain.user.User;
import com.gdcd.back.domain.user.block.Block;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class BlockDocumentListener extends AbstractMongoEventListener<Block> {
    private final BlockSequenceGeneratorService blockSequenceGeneratorService;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Block> event){
        if (event.getSource().getId() != null) return;
        event.getSource().setId(blockSequenceGeneratorService.generateSequence(Block.BLOCK_SEQUENCE_NAME));
    }
}
