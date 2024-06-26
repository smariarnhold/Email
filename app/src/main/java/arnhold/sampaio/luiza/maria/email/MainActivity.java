package arnhold.sampaio.luiza.maria.email;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity { // a classe MainActivity eh a principal da aplicacao

    @Override
    protected void onCreate(Bundle savedInstanceState) { // onCreate eh chamado toda vez que a tela eh criada
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //constroi os elementos de interface definidos no arq xml

        Button btnEnviar = (Button) findViewById(R.id.btnEnviar); // pegamos o elemento do botao

        btnEnviar.setOnClickListener(new View.OnClickListener() { // estamos definindo uma acao ao clicar no botao
            @Override
            public void onClick(View v) {
                EditText etEmail = (EditText) findViewById(R.id.etEmail); // pegamos o elemento de texto (do email)
                String email = etEmail.getText().toString(); // pegamos o conteudo dentro do elemento selecionado (o email)

                EditText etAssunto = (EditText) findViewById(R.id.etAssunto); // pegamos o elemento de texto (do assunto)
                String assunto = etAssunto.getText().toString(); // pegamos o conteudo dentro do elemento selecionado (o assunto)

                EditText etTexto = (EditText) findViewById(R.id.etTexto); // pegamos o elemento de texto (do corpo do email)
                String texto = etTexto.getText().toString(); // pegamos o conteudo dentro do elemento selecionado (a descricao detalhada)

                Intent i = new Intent(Intent.ACTION_SENDTO); // criamos um intent, sua acao deve buscar apps para executar o programa

                i.setData(Uri.parse("mailto:")); // definimos como obrigatorio apps de envio de email

                String[] emails = new String[]{email}; // lista para armazenar todos os emails ja enviados
                i.putExtra(Intent.EXTRA_EMAIL, emails); // estamos fazendo uma lista para armazenar a string de email
                i.putExtra(Intent.EXTRA_SUBJECT, assunto); // estamos fazendo uma lista para armazenar a string de assunto
                i.putExtra(Intent.EXTRA_TEXT, texto); // estamos fazendo uma lista para armazenar a string de texto

                try { // tentativa de execucao do email
                    startActivity(Intent.createChooser(i, "Escolha o APP")); // abri um menu para selecionar o app de preferencia
                }
                catch (ActivityNotFoundException e) {
                    Toast.makeText(MainActivity.this, "Não há nenhum app que eu posso realizar essa operação", Toast.LENGTH_LONG).show(); // caso nao haja nenhum app assim
                }
            }
        });

    }
}