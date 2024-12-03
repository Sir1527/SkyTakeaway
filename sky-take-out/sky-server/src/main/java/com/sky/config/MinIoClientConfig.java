package com.sky.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinIoClientConfig {
    /**
     * minio配置
     */
    @Value("${minio.host}")
    private String endpoint;
    @Value("${minio.accessKey}")
    private String accessKey;
    @Value("${minio.secretKey}")
    private String secretKey;
    @Value("${minio.bucketName}")
    private String bucketName;

    /**
     * 注入minio客户端
     *
     * @return minio客户端对象
     */
    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder().endpoint(endpoint).credentials(accessKey, secretKey).build();
    }
}
