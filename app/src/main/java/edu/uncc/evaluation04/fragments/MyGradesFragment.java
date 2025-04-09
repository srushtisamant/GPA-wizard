
package edu.uncc.evaluation04.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import edu.uncc.evaluation04.R;
import edu.uncc.evaluation04.databinding.FragmentMyGradesBinding;
import edu.uncc.evaluation04.databinding.GradeRowItemBinding;
import edu.uncc.evaluation04.db.AppDatabase;
import edu.uncc.evaluation04.models.Grade;

public class MyGradesFragment extends Fragment {
    public MyGradesFragment() {
        // Required empty public constructor
    }

    FragmentMyGradesBinding binding;
    private ArrayList<Grade> mGrades = new ArrayList<>();

    MyGradesAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMyGradesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("My Grades");
        mGrades.clear();
        mGrades.addAll(mListener.getAllGrades());
        calculateAndDisplayGPA();
        adapter = new MyGradesAdapter();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerView.setAdapter(adapter);

        binding.buttonAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.gotoAddGrade();
            }
        });

        binding.buttonUpdatePin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.gotoUpdatePin();
            }
        });

    }

//    private void calculateAndDisplayGPA(){
//
//    }

    private void calculateAndDisplayGPA(){
        if(mGrades.size() == 0){
            binding.textViewGPA.setText("GPA: 4.0");
            binding.textViewHours.setText("Hours: 0.0");
        } else {
            double acc = 0.0;
            double hours = 0.0;
            for (Grade grade: mGrades) {
                double letterGrade = 0.0;
                if(grade.getLetterGrade().equals("A")){
                    letterGrade = 4.0;
                } else if(grade.getLetterGrade().equals("B")){
                    letterGrade = 3.0;
                } else if(grade.getLetterGrade().equals("C")){
                    letterGrade = 2.0;
                } else if(grade.getLetterGrade().equals("D")){
                    letterGrade = 1.0;
                }
                acc = acc + grade.getCourseHours() * letterGrade;
                hours = hours + grade.getCourseHours();
            }

            if(hours == 0) {
                binding.textViewGPA.setText("GPA: 4.0");
                binding.textViewHours.setText("Hours: 0.0");
            } else {
                acc = acc / hours;
                binding.textViewGPA.setText("GPA: " + String.format("%.2f", acc));
                binding.textViewHours.setText("Hours: " + String.format("%.2f", hours));
            }
        }
    }

    class MyGradesAdapter extends RecyclerView.Adapter<MyGradesAdapter.GradesViewHolder> {


        @NonNull
        @Override
        public GradesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            GradeRowItemBinding itemBinding = GradeRowItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new GradesViewHolder(itemBinding);
        }

        @Override
        public void onBindViewHolder(@NonNull GradesViewHolder holder, int position) {
            holder.setupUI(mGrades.get(position));
        }

        @Override
        public int getItemCount() {
            return mGrades.size();
        }

        class GradesViewHolder extends RecyclerView.ViewHolder {
            GradeRowItemBinding itemBinding;
            Grade mGrade;
            public GradesViewHolder(GradeRowItemBinding itemBinding) {
                super(itemBinding.getRoot());
                this.itemBinding = itemBinding;
            }

            public void setupUI(Grade grade){
                mGrade = grade;
                itemBinding.textViewCourseName.setText(grade.getCourseName());
                itemBinding.textViewCreditHours.setText(String.format("%.2f", grade.getCourseHours())+" Credit Hours");
                itemBinding.textViewCourseNumber.setText(grade.getCourseNumber());
                itemBinding.textViewLetterGrade.setText(grade.letterGrade.toUpperCase());
                itemBinding.textViewSemester.setText(grade.semester.toString());




                itemBinding.imageViewDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.deleteGrade(mGrade);
                        mGrades.remove(mGrade);
                        calculateAndDisplayGPA();
                        adapter.notifyDataSetChanged();
                    }
                });

            }
        }
    }




    MyGradesListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof MyGradesListener) {
            mListener = (MyGradesListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement MyGradesListener");
        }
    }

    public interface MyGradesListener {
        void gotoAddGrade();
        void gotoUpdatePin();
        ArrayList<Grade> getAllGrades();
        void deleteGrade(Grade grade);
    }
}
