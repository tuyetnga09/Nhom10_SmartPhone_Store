package com.example.smartphone_store.DAO;

import jakarta.validation.constraints.NotBlank;

public class BatteryDAO {

    @NotBlank(message = "Mã trống!")
    private String code;

    @NotBlank(message = "Tên trống!")
    private String name;

    @NotBlank(message = "Người nhập trống!")
    private String personCreate;

    @NotBlank(message = "Người sửa trống!")
    private String personUpdate;
}
