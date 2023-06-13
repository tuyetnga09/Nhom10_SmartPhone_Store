package com.example.smartphone_store.DAO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ImagesDAO {

    @NotNull(message = "Chưa chọn ảnh!")
    private MultipartFile fileImages;

    private String describe;

    @NotBlank(message = "Người nhập trống!")
    private String personCreate;

    private String personUpdate;

}