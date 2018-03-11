package com.example.qq;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class myAdapter3 extends RecyclerView.Adapter{
    private cellData_3[] Data3 = new cellData_3[]{new cellData_3("2018-1-17","AB12345678")};
    class ViewHolder extends RecyclerView.ViewHolder {


        private TextView code;
        private TextView date;

        public ViewHolder(View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.date_re);
            code = itemView.findViewById(R.id.code_re);
        }



        public TextView getDate() {
            return date;
        }
        public TextView getCode() {return code;}

    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cell3, null);
        ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder,final int position) {
        myAdapter3.ViewHolder vh = (myAdapter3.ViewHolder) holder;
        cellData_3 cd  =Data3[position];
        vh.getCode().setText(cd.getCode());
        vh.getDate().setText(cd.getDate());
    }
    @Override
    public int getItemCount(){
        return Data3.length;
    }
    private  int idx=-1;
    public void add(cellData_3 obj) {
        idx++;
        if (idx==0) {
            Data3[0].setCode(obj.getCode());
            Data3[0].setDate(obj.getDate());
        }
        else if (idx>=Data3.length) {
            cellData_3[] newData=new cellData_3[Data3.length+1];
            System.arraycopy(Data3,0,newData,0,Data3.length);
            Data3 = newData;
        }
        Data3[idx] = obj;
    }
    public void emptyData3() {
        cellData_3[] empty = new cellData_3[]{new cellData_3("2018-1-17","AB12345678")};
        Data3 = empty;
        this.idx = -1;
    }

}
