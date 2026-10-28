package com.example.midtest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {
    List<StudentModel> students;
    Context context;
    DBHelper dbHelper;

    public StudentAdapter(List<StudentModel> students, Context context) {
        this.students = students;
        this.context = context;
        dbHelper = new DBHelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.student_item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        final StudentModel studentModel = students.get(position);
        holder.textViewID.setText(Integer.toString(studentModel.getId()));
        holder.editTextName.setText(studentModel.getName());
        holder.editTextStudentID.setText(studentModel.getStudentId());
        holder.editTextAge.setText(String.valueOf(studentModel.getAge()));

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strName = holder.editTextName.getText().toString().trim();
                String strStudentID = holder.editTextStudentID.getText().toString().trim();
                String strAge = holder.editTextAge.getText().toString().trim();

                if (strName.isEmpty() || strStudentID.isEmpty() || strAge.isEmpty()) {
                    Toast.makeText(context, "Input cannot be empty or spaces only!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!strStudentID.matches("\\d+")) {
                    Toast.makeText(context, "Student ID must be a number!", Toast.LENGTH_SHORT).show();
                    return;
                }

                int age = Integer.parseInt(strAge);
                if (age < 17 || age > 50) {
                    Toast.makeText(context, "Age must be between 17 and 50", Toast.LENGTH_SHORT).show();
                    return;
                }

                dbHelper.updateStudent(new StudentModel(studentModel.getId(), strName, strStudentID, age));
                Toast.makeText(context, "Updated successfully", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.deleteStudent(studentModel.getId());
                students.remove(position);
                notifyDataSetChanged();
                Toast.makeText(context, "Deleted successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewID;
        EditText editTextName, editTextStudentID, editTextAge;
        Button btnEdit, btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewID = itemView.findViewById(R.id.textViewID);
            editTextName = itemView.findViewById(R.id.editTextName);
            editTextStudentID = itemView.findViewById(R.id.editTextStudentID);
            editTextAge = itemView.findViewById(R.id.editTextAge);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}