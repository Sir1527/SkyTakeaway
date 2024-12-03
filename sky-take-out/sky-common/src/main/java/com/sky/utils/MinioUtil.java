package com.sky.utils;

import com.sky.dto.FileInfoDTO;
import com.sky.enumeration.ViewContentTypeEnum;
import io.minio.*;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class MinioUtil {

    /**
     * minio配置
     */
    @Value("${minio.host}")
    private String endpoint;
    @Autowired
    private MinioClient minioClient;

    /**
     * 创建一个桶
     */
    public void createBucket(String bucket) throws Exception {
        boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());
        if (!found) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
        }
    }

    /**
     * 列出一个桶中的所有文件和目录
     */
    public List<FileInfoDTO> listFiles(String bucket) throws Exception {
        Iterable<Result<Item>> results = minioClient.listObjects(
                ListObjectsArgs.builder().bucket(bucket).recursive(true).build());

        List<FileInfoDTO> infos = new ArrayList<>();
        results.forEach(r->{
            FileInfoDTO info = new FileInfoDTO();
            try {
                Item item = r.get();
                info.setFilename(item.objectName());
                info.setDirectory(item.isDir());
                info.setFilepath(endpoint+"/"+bucket+"/"+item.objectName());
                infos.add(info);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return infos;
    }


    /**
     * 列出所有的桶
     */
    public List<String> listBuckets() throws Exception {
        List<Bucket> list = minioClient.listBuckets();
        List<String> names = new ArrayList<>();
        list.forEach(b -> {
            names.add(b.name());
        });
        return names;
    }

    /**
     * 上传一个文件
     */
    public String uploadFile(InputStream stream, String bucket, String objectName) throws Exception {
        ObjectWriteResponse objectWriteResponse = minioClient.putObject(PutObjectArgs.builder()
                .bucket(bucket)
                .object(objectName)
                .contentType(ViewContentTypeEnum.getContentType(objectName))
                .stream(stream, -1, 10485760).build());
        return endpoint + "/" + bucket + "/" + objectWriteResponse.object();
    }

    /**
     * 下载一个文件
     */
    public InputStream download(String bucket, String objectName) throws Exception {
        InputStream stream = minioClient.getObject(
                GetObjectArgs.builder().bucket(bucket).object(objectName).build());
        return stream;
    }
}
