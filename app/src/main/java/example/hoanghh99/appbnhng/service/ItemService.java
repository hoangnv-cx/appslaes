package example.hoanghh99.appbnhng.service;

import java.util.List;

import example.hoanghh99.appbnhng.DTO.ItemDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ItemService {
    @GET("web/item/id?id={id}")
    Call<ItemDTO> getItemDetails(
            @Path("id") String id
    );

    @GET("web/item/list")
    Call<List<ItemDTO>> getItemList();
}
