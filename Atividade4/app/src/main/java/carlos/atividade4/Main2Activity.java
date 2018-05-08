package carlos.atividade4;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import carlos.atividade4.util.DBAgenda;

public class Main2Activity extends Activity {

    private EditText edtNome, edtTelefone, edtEmail;
    private Button btnSalvar;
    private DBAgenda database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        database = new DBAgenda(this);

        btnSalvar = findViewById(R.id.btnSalvar);
        edtNome = findViewById(R.id.edtNome);
        edtTelefone = findViewById(R.id.edtTelefone);
        edtEmail = findViewById(R.id.edtEmail);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Agenda agenda = new Agenda();

                agenda.setNome(edtNome.getText().toString());
                agenda.setTelefone(edtTelefone.getText().toString());
                agenda.setEmail(edtEmail.getText().toString());

                if(agenda.getNome().isEmpty()){
                    Toast.makeText(Main2Activity.this, "Nome não informado!", Toast.LENGTH_LONG).show();
                    return;
                }

                if(agenda.getTelefone().isEmpty()){
                    Toast.makeText(Main2Activity.this, "Telefone não informado!", Toast.LENGTH_LONG).show();
                    return;
                }

                if(agenda.getEmail().isEmpty()){
                    Toast.makeText(Main2Activity.this, "E-mail não informado!", Toast.LENGTH_LONG).show();
                    return;
                }

                database.addContatoAgenda(agenda);

                Toast.makeText(getBaseContext(), edtNome.getText().toString()+" adicionado à agenda!", Toast.LENGTH_SHORT).show();

                Intent it3 = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(it3);
            }
        });
    }
}
