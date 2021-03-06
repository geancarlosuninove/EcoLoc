package br.com.fabappu9.ecoloc.network;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import br.com.fabappu9.ecoloc.DTO.PontoDto;
import br.com.fabappu9.ecoloc.Model.Resposta;
import br.com.fabappu9.ecoloc.Model.RespostaLogin;
import br.com.fabappu9.ecoloc.Model.RespostaPonto;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
//import retrofit.RestAdapter;
//import retrofit.client.OkClient;
//import retrofit.http.GET;
//import retrofit.http.Path;
import retrofit2.http.Query;



public class APIClient {

    private static Retrofit REST_ADAPTER;

    public APIClient(){
        createAdapterIfNeeded();
    }

    private static void createAdapterIfNeeded() {
        if(REST_ADAPTER == null){
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            REST_ADAPTER = new Retrofit
                    .Builder()
                    .baseUrl("http://devjan.esy.es/ws_app/v1/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
    }

    public RestServices getRestService(){
        return REST_ADAPTER.create(RestServices.class);
    }

    public interface RestServices{

        @GET("usuarioDTO.php")
        Call<Resposta> setUsuarioDTO(
                @Query("CHAVE") String chave,
                @Query("CHAMADA") String chamada,
                @Query("NOME") String nome,
                @Query("LOGIN") String login,
                @Query("SENHA") String senha
        );
       @GET("usuarioDTO.php")
       Call<RespostaLogin> setUsuarioLoginDTO(
                @Query("CHAVE") String chave,
                @Query("CHAMADA") String chamada,
                @Query("LOGIN") String login,
                @Query("SENHA") String senha
        );
        @GET("ponto.php")
        Call<RespostaPonto> setPontoDTO(
                @Query("CHAVE") String chave,
                @Query("CHAMADA") String chamada,
                @Query("DESCRICAO") String nome,
                @Query("TIPOMATERIAL") String tipomaterial,
                @Query("LATITUDE") String latitude,
                @Query("LONGETUDE") String longitude
        );
        @GET("ponto.php")
        Call<List<PontoDto>> getPontoDTO(
                @Query("CHAVE") String chave,
                @Query("CHAMADA") String chamada,
                @Query("PARAM") String nome
        );
    }
}