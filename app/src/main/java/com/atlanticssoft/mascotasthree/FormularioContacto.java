package com.atlanticssoft.mascotasthree;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class FormularioContacto extends AppCompatActivity{

    // Declaración de variables globales
    private EditText etNombre,etEmail,etMensaje;
    private Button btnEnviar;
    String sEmail, sPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_contacto);

        // Habilito mi actionbar_favoritos personalizado, Recuerda Adicionar la propiedad android:parentActivityName=".MainActivity" en el Manifest para que funcione
        Toolbar miActionBar_Favoritos = (Toolbar) findViewById(R.id.miActionBar_Favoritos);
        setSupportActionBar(miActionBar_Favoritos);

        // Habilito la navegación hacia atrás
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        // Enlazo los views, asigno variables
        etNombre  = (EditText) findViewById(R.id.etNombre);
        etEmail   = (EditText) findViewById(R.id.etEmail);
        etMensaje = (EditText) findViewById(R.id.etMensaje);
        btnEnviar= (Button) findViewById(R.id.btnEnviar);

        // Credenciales del correo electrónico del remitente
        sEmail = "nestor.martinez.c@uniautonoma.edu.co";
        sPassword = "sksmartinez";

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inicializo las propiedades
                Properties properties = new Properties();
                properties.put("mail.smtp.auth","true");
                properties.put("mail.smtp.starttls.enable","true");
                properties.put("mail.smtp.host","smtp.gmail.com");
                properties.put("mail.smtp.port","587");

                // Inicializo session
                Session session = Session.getInstance(properties, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(sEmail,sPassword);
                    }
                });

                try
                {
                    // Inicializo el contenido del Email
                    MimeMessage message = new MimeMessage(session);

                    // Correo electrónico del remitente
                    message.setFrom(new InternetAddress(sEmail));

                    // Receptor de E-mail
                    message.setRecipients(Message.RecipientType.TO,
                            InternetAddress.parse(etEmail.getText().toString().trim()));

                    // Asunto del E-mail
                    message.setSubject(etNombre.getText().toString().trim());

                    // Mensaje del E-mail
                    message.setText(etMensaje.getText().toString().trim());

                    // Enviar E-mail
                    new SendMail().execute(message);

                } catch (AddressException e) {
                    e.printStackTrace();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    private class SendMail extends AsyncTask<Message,String,String> {

        // Inicializo progress Dialog
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // Crear y mostrar el progress dialog
            progressDialog = ProgressDialog.show(FormularioContacto.this, "Por favor, espere", "Enviando mensaje...",true,false);
        }

        @Override
        protected String doInBackground(Message... messages)
        {
            try {

                //  Cuando el envío sea exitoso
                Transport.send(messages[0]);
                return "Success";


            } catch (MessagingException e) {
                // Cuando ocurra un error
                e.printStackTrace();
                return "Error";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            // Descartar progress dialog
            progressDialog.dismiss();

            if (s.equals("Success"))
            {
                // Cuando sea exitoso

                // Inicializo el alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(FormularioContacto.this);
                builder.setTitle(Html.fromHtml("<font color='#509324'>Success</font>"));
                builder.setMessage("Mensaje enviado !");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Limpio todos los edit text
                        etEmail.setText("");
                        etNombre.setText("");
                        etMensaje.setText("");
                    }
                });

                // Muestro el alert dialog
                builder.show();
            } else {
                // Cuando ocurra error
                Toast.makeText(getApplicationContext(),"¿Algo salió mal?, verifica tu conexión a Internet",Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Programo el botón de Volver atrás del teléfono
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {

        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }
}