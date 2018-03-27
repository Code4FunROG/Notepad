package mz.mondlhane.sinrostro.notepad;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //Declaracao das variaveis
    Button btngravar;
    Button btnapagar;
    Button btnmostrar;
    EditText textIntro;
    TextView textVizualizar;

    //SharedPreferences
    SharedPreferences sharedPreferences;

    private static String VALUE_TAG = "VALOR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializando as views
        btngravar = (Button) findViewById(R.id.btnGravar);
        btnmostrar = (Button) findViewById(R.id.btnMostrar);
        btnapagar = (Button) findViewById(R.id.btnApagar);
        textIntro = (EditText) findViewById(R.id.textIntro);
        textVizualizar = (TextView) findViewById(R.id.textVisualizar);

        //Inicializando SharedPreferences
        sharedPreferences = getPreferences(Context.MODE_PRIVATE);

        //Clique Listener do botao gravar
        btngravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(VALUE_TAG, textIntro.getText().toString());
                editor.apply();
                Toast.makeText(MainActivity.this,"Gravado com sucesso!!", Toast.LENGTH_LONG).show();
            }
        });

        //Click listener do botao mostrar
        btnmostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textVizualizar.setText(sharedPreferences.getString(VALUE_TAG, "N"));
            }
        });

        //Click listener do botao apagar
        btnapagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Texto a apagar
                String texto = textIntro.getText().toString();
                //Verificar se o tamanho do texto é maior
                if(texto.length() > 0){
                    //Indicando o ultimo a caracter a apagar
                    int indice = texto.length() - 1;
                    //Colocando uma subsyting - texto anterior menos o ultimo caracter
                    textIntro.setText(texto.substring(0, indice));
                    //Indicando a posicao do cursor (final da string)
                    textIntro.setSelection(indice);
                    //Colocando o cursor como visivel
                    textIntro.setCursorVisible(true);
                }
            }
        });

    }
}
