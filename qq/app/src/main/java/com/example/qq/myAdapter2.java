package com.example.qq;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;




class MyAdapter2 extends RecyclerView.Adapter{
    private cellData_2[] Data2 = new cellData_2[]{new cellData_2(2,"飲食")};
    class ViewHolder extends RecyclerView.ViewHolder {


        private TextView item;
        private TextView total;


        public ViewHolder(View itemView) {
            super(itemView);

            item = itemView.findViewById(R.id.item_pc);
            total = itemView.findViewById((R.id.total_pc));
        }



        public TextView getItem() {
            return item;
        }

        public TextView getTotal() {
            return total;
        }

    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cell_2, null);
        ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder,final int position) {
        ViewHolder vh = (ViewHolder) holder;
        cellData_2 cd  =Data2[position];
        vh.getTotal().setText(Integer.toString(cd.getTotal()));
        vh.getItem().setText(cd.getItem());
    }
    @Override
    public int getItemCount(){
        return Data2.length;
    }
    private  int idx=-1;
    public void add(cellData_2 obj) {
        idx++;
        if (idx==0) {
            Data2[0].setTotal(obj.getTotal());
            Data2[0].setItem(obj.getItem());
        }
        else if (idx>=Data2.length) {
            cellData_2[] newData=new cellData_2[Data2.length+1];
            System.arraycopy(Data2,0,newData,0,Data2.length);
            Data2 = newData;
        }
        Data2[idx] = obj;
    }
    public void emptyData2() {
        cellData_2[] empty = new cellData_2[]{new cellData_2(2,"飲食")};
        Data2 = empty;
        this.idx = -1;
    }
}