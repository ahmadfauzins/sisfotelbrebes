package com.fauzi.sisfotelbrebes.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.fauzi.sisfotelbrebes.R;
import com.fauzi.sisfotelbrebes.model.PengajuanModel;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterStatus extends RecyclerView.Adapter<AdapterStatus.MyViewHolder> {

    List<PengajuanModel> mahasiswa,mhsFilter;
    private Context context;
    private RecyclerViewClickListener mListener;

    public AdapterStatus(List<PengajuanModel> mahasiswa, Context context) {
        this.mahasiswa = mahasiswa;
        this.mhsFilter = mahasiswa;
        this.context = context;
    }
//    public AdapterStatus(List<PengajuanModel> mahasiswa, Context context, RecyclerViewClickListener listener) {
//        this.mahasiswa = mahasiswa;
//        this.mhsFilter = mahasiswa;
//        this.context = context;
//        this.mListener = listener;
//    }




    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_status, parent, false);
        return new MyViewHolder(view, mListener);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        holder.mName.setText(mahasiswa.get(position).getSite_name());
        holder.mType.setText(mahasiswa.get(position).getAlamat());
        holder.mDate.setText(mahasiswa.get(position).getStatus());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.skipMemoryCache(true);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
        requestOptions.placeholder(R.drawable.broadcast);
        requestOptions.error(R.drawable.broadcast);

        Glide.with(context)
                .load(mahasiswa.get(position).getOperator())
                .apply(requestOptions)
                .into(holder.mPicture);

//        final Boolean love = pets.get(position).getLove();
//
//        if (love){
//            holder.mLove.setImageResource(R.drawable.likeon);
//        } else {
//            holder.mLove.setImageResource(R.drawable.likeof);
//        }

    }

    @Override
    public int getItemCount() {
        return mahasiswa.size();
    }

//    @Override
//    public Filter getFilter() {
//        if (filter==null) {
//            filter=new CustomFilter((ArrayList<PengajuanModel>) mhsFilter,this);
//
//        }
//        return filter;
//    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private RecyclerViewClickListener mListener;
        private CircleImageView mPicture;
//        private ImageView mLove;
        private TextView mName, mType, mDate;
        private RelativeLayout mRowContainer;

        public MyViewHolder(View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            mPicture = itemView.findViewById(R.id.imgBuah);
            mName = itemView.findViewById(R.id.name);
            mType = itemView.findViewById(R.id.manfaat);
//            mLove = itemView.findViewById(R.id.love);
            mDate = itemView.findViewById(R.id.type);
            mRowContainer = itemView.findViewById(R.id.row_container);

            mListener = listener;
            mRowContainer.setOnClickListener(this);
//            mLove.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.row_container:
                    mListener.onRowClick(mRowContainer, getAdapterPosition());
                    break;

                default:
                    break;
            }
        }
    }

    public interface RecyclerViewClickListener {
        void onRowClick(View view, int position);

        void onLoveClick(View view, int position);
//        void onLoveClick(View view, int position);
    }

}
