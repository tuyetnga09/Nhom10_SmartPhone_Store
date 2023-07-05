package com.example.smartphone_store.DAO;

import com.example.smartphone_store.entity.Battery;
import com.example.smartphone_store.entity.Capacity;
import com.example.smartphone_store.entity.Category;
import com.example.smartphone_store.entity.Chip;
import com.example.smartphone_store.entity.Color;
import com.example.smartphone_store.entity.Manufacture;
import com.example.smartphone_store.entity.Product;
import com.example.smartphone_store.entity.Ram;
import com.example.smartphone_store.entity.Screen;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class ProductDetailDAO {

    @NotBlank(message = "Mã không được để trống!")
    private String code;

    @NotBlank(message = "Tên không được để trống!")
    private String name;

    private LocalDate dateCreate;

    private LocalDate dateUpdate;

    @NotNull(message = "Chưa nhập tên người tạo!")
    private String personCreate;

    private String personUpdate;

    private int status;

    private String describe;

    @NotNull(message = "* Mời Nhập Giá Sản Phẩm!")
    private Float price;

    private MultipartFile images;

    private Integer quantity;

    private Capacity capacity;

    private Color color;

    private Manufacture manufacture;

    private Category category;

    private Battery battery;

    private Chip chip;

    private Ram ram;

    private Screen screen;

    private Product product;
}
