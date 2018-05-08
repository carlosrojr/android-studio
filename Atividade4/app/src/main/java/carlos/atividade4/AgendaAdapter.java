package carlos.atividade4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AgendaAdapter extends ArrayAdapter<Agenda> {

    private final Context context;
    private final ArrayList<Agenda> agenda;

    public AgendaAdapter(Context context, ArrayList<Agenda> agenda) {
        super(context, R.layout.listview, agenda);
        this.context = context;
        this.agenda = agenda;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.listview, parent, false);

        TextView nome = rowView.findViewById(R.id.txtNome);
        TextView telefone =  rowView.findViewById(R.id.txtTelefone);

        nome.setText(agenda.get(position).getNome());
        telefone.setText(agenda.get(position).getTelefone());

        return rowView;
    }
}
