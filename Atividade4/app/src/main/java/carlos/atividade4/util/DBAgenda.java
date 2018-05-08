package carlos.atividade4.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import carlos.atividade4.Agenda;

public class DBAgenda extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String  DATABASE_NAME = "AgendaDB";

    private static final String TABELA_AGENDA = "agenda";
    private static final String ID = "id";
    private static final String NOME = "nome";
    private static final String TELEFONE = "telefone";
    private static final String EMAIL = "email";
    private static final String[] COLUNAS = { ID, NOME, TELEFONE, EMAIL };

    public DBAgenda(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE agenda("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "nome TEXT,"+
                "telefone TEXT,"+
                "email TEXT)";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS agenda");
        this.onCreate(db);
    }

    public void addContatoAgenda(Agenda agenda) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NOME, agenda.getNome());
        values.put(TELEFONE, agenda.getTelefone());
        values.put(EMAIL, agenda.getEmail());
        db.insert(TABELA_AGENDA, null, values);
        db.close();
    }

    public Agenda getAgenda(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABELA_AGENDA,
                COLUNAS,
                " id = ?",
                new String[] { String.valueOf(id) },
                null,
                null,
                null,
                null);
        if (cursor == null) {
            return null;
        } else {
            cursor.moveToFirst();
            Agenda agenda = cursorToContatoAgenda(cursor);
            return agenda;
        }
    }

    private Agenda cursorToContatoAgenda(Cursor cursor) {
        Agenda agenda = new Agenda();
        agenda.setId(Integer.parseInt(cursor.getString(0)));
        agenda.setNome(cursor.getString(1));
        agenda.setTelefone(cursor.getString(2));
        return agenda;
    }

    public ArrayList<Agenda> getAllContatosAgenda(){
        ArrayList<Agenda> listaContatos = new ArrayList<Agenda>();
        String query = "SELECT * FROM "+TABELA_AGENDA;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do {
                Agenda agenda = cursorToContatoAgenda(cursor);
                listaContatos.add(agenda);
            } while (cursor.moveToNext());
        }
        return listaContatos;
    }

    public ArrayList<Agenda> getSearchAgenda(String search) {
        ArrayList<Agenda> listaContatos = new ArrayList<Agenda>();
        String query = "SELECT * FROM " + TABELA_AGENDA + " WHERE nome LIKE '%" + search + "%'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Agenda contato = cursorToContatoAgenda(cursor);
                listaContatos.add(contato);
            } while (cursor.moveToNext());
        }
        return listaContatos;
    }
}
