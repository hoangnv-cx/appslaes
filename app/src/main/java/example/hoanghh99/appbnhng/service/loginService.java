package example.hoanghh99.appbnhng.service;

import example.hoanghh99.appbnhng.DTO.UserDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface loginService {
    @POST("authenticate")
    Call<UserDTO> postDataLogin(@Body UserDTO userDTO);
    @POST("login")
    Call<UserDTO> postDataSignup(@Body UserDTO userDTO);
}
