package com.example.qq;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;




class MyAdapter extends RecyclerView.Adapter{
    private cellData[] Data2 = new cellData[]{new cellData(2,"飲食","消夜")};
    class ViewHolder extends RecyclerView.ViewHolder {


        private TextView item;
        private TextView amount;
        private TextView name;

        public ViewHolder(View itemView) {
            super(itemView);

            item = itemView.findViewById(R.id.item);
            amount = itemView.findViewById((R.id.amount));
            name = itemView.findViewById(R.id.name);

        }



        public TextView getItem() {
            return item;
        }

        public TextView getAmount() {
            return amount;
        }
        public TextView getName() {return name;}

    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cell, null);
        ViewHolder vh = new ViewHolder(view);
        view.getBackground().setAlpha(150);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder,final int position) {
        ViewHolder vh = (ViewHolder) holder;
        cellData cd  =Data2[position];
        vh.getAmount().setText(Integer.toString(cd.getAmount()));
        vh.getItem().setText(cd.getitem());
        vh.getName().setText(cd.getName());
    }
    @Override
    public int getItemCount(){
        return Data2.length;
    }
    private  int idx=-1;
    public void add(cellData obj) {
        idx++;
        if (idx==0) {
            Data2[0].setAmount(obj.getAmount());
            Data2[0].setItem(obj.getitem());
            Data2[0].setName(obj.getName());
        }
        else if (idx>=Data2.length) {
            cellData[] newData=new cellData[Data2.length+1];
            System.arraycopy(Data2,0,newData,0,Data2.length);
            Data2 = newData;
        }
        Data2[idx] = obj;
    }
    public void emptyData2() {
        cellData[] empty = new cellData[]{new cellData(2,"飲食","消夜")};
        Data2 = empty;
        this.idx = -1;
    }

}