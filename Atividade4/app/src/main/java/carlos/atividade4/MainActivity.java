package carlos.atividade4;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

import carlos.atividade4.util.DBAgenda;

public class MainActivity extends Activity {

    private Button btnNovoContato, btnPesquisar;
    private DBAgenda database;
    ArrayList<Agenda> listAgenda;
    private EditText edtPesquisa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = new DBAgenda(this);

        edtPesquisa = findViewById(R.id.edtPesquisa);

        btnNovoContato = findViewById(R.id.btnNovoContato);
        btnPesquisar = findViewById(R.id.btnPesquisar);

        btnNovoContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent it1 = new Intent(MainActivity.this, Main2Activity.class );
                startActivity(it1);
            }
        });

        btnPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(edtPesquisa.getText().toString().isEmpty()) {
                    ListView list = findViewById(R.id.listAgenda);
                    listAgenda = database.getAllContatosAgenda();
                    AgendaAdapter adapter = new AgendaAdapter(MainActivity.this, listAgenda);
                    list.setAdapter(adapter);

                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view,
                                                int position, long id) {
                            String telefone = listAgenda.get(position).getTelefone().toString();
                            Uri uri = Uri.parse("tel:" + telefone);
                            Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                            startActivity(intent);
                        }
                    });

                }else
                        {
                            ListView lista = (ListView) findViewById(R.id.listAgenda);
                            listAgenda = database.getSearchAgenda(edtPesquisa.getText().toString());
                            AgendaAdapter adapter = new AgendaAdapter(MainActivity.this, listAgenda);
                            lista.setAdapter(adapter);

                            lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view,
                                                        int position, long id) {
                                    String telefone = listAgenda.get(position).getTelefone().toString();
                                    Uri uri = Uri.parse("tel:"+telefone);
                                    Intent intent = new Intent(Intent.ACTION_DIAL,uri);
                                    startActivity(intent);
                                }
                            });

                        }
                }

        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        ListView list = (ListView) findViewById(R.id.listAgenda);
        listAgenda = database.getAllContatosAgenda();
        AgendaAdapter adapter = new AgendaAdapter(this, listAgenda);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                String telefone = listAgenda.get(position).getTelefone().toString();
                Uri uri = Uri.parse("tel:"+telefone);
                Intent intent = new Intent(Intent.ACTION_DIAL,uri);
                startActivity(intent);
            }
        });
    }

}
