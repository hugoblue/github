package com.ehouse.batch.batch001.listener;

import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.stereotype.Component;

@Component("chunkListener")
public class ChunkListener implements org.springframework.batch.core.ChunkListener {
    @Override
    public void beforeChunk(ChunkContext chunkContext) {
        System.out.println("************************ before chunk");
    }

    @Override
    public void afterChunk(ChunkContext chunkContext) {
        System.out.println("************************ after chunk");
    }

    @Override
    public void afterChunkError(ChunkContext chunkContext) {
        System.out.println("************************ error chunk");
    }
}
