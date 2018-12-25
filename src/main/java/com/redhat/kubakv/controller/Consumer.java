package com.redhat.kubakv.controller;

import com.redhat.kubakv.model.GenericResponse;
import com.redhat.kubakc.model.Metadata;
import com.redhat.kubakv.repository.MetadataRepository;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class Consumer {

    @Autowired
    GenericResponse genericResponse;

    @Autowired
    MetadataRepository metadataRepository;

    @GetMapping("/consumer")
    public ResponseEntity getConsumers() {
        List<Metadata> consumersList = new ArrayList<>();
        metadataRepository.findAll().forEach(metadata -> {
            if (metadata == null) return;
            consumersList.add(metadata);
        });
        genericResponse.setData(consumersList);
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(genericResponse.getJsonPayload());
    }

    @DeleteMapping("/consumer/{id}")
    public ResponseEntity deleteConsumer(@PathVariable String id) {
        Metadata m = metadataRepository.findById(id).orElse(null);
        if (m == null) return ResponseEntity.notFound().build();
        String consumerUrl = String.format("http://%s:%s/topic/state", m.getHostname(), m.getServerPort());
        OkHttpClient okClient = new OkHttpClient();
        Request r = new Request.Builder().url(consumerUrl).delete().build();
        try {
            Response response = okClient.newCall(r).execute();
            String res = response.body().string();
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(res);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
