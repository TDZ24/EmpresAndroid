package com.example.empresa;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class SesionFragment extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener{

    EditText jetcorreo, jetclave;
    Button jbtingresar;
    TextView jtvregistrar;
    RequestQueue rq;
    JsonRequest jrq;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_sesion, container, false);
        View vista = inflater.inflate(R.layout.fragment_sesion,container,false);
        jetcorreo=vista.findViewById(R.id.etcorreo);
        jetclave=vista.findViewById(R.id.etclave);
        jtvregistrar=vista.findViewById(R.id.tvregistrar);
        jbtingresar=vista.findViewById(R.id.btingresar);

        //Lanzar un metodo unido al evento click del objeto jbtingresar
        jbtingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Iniciar_sesion();
            }
        });

        jtvregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intusuarios = new Intent(getContext(), UsuariosActivity.class);
                startActivity(intusuarios);
            }
        });

        rq = Volley.newRequestQueue(getContext());//Conexion a internet
        return vista;
    }

    private void Iniciar_sesion(){
        if (jetcorreo.getText().toString().isEmpty() ||
                jetclave.getText().toString().isEmpty()){
            Toast.makeText(getContext(),"El correo y la clave son requeridos",Toast.LENGTH_SHORT).show();
            jetcorreo.requestFocus();
        }
        else{
            String url = "http://172.16.58.9:80/WebServices/ingreso.php?correo="+jetcorreo.getText().toString()+"&clave="+jetclave.getText().toString();
            jrq = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
            rq.add(jrq);
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), "Error, usuario no registrado", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(getContext(), "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();

    }
}