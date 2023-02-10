package ru.Savenko.cbr;

import retrofit.http.GET;
import retrofit.http.Query;
import ru.Savenko.DTO.ValCurs;

public interface Cbr {
    @GET("/scripts/XML_daily.asp")
    ValCurs getExchange(@Query("date_req") String date);
}
