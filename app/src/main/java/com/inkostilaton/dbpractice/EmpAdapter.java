package com.inkostilaton.dbpractice;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EmpAdapter extends DataAdapter {
    private VisibilityList<Employee> employees = new VisibilityList<>();

    public EmpAdapter(Context context, RecyclerView recyclerView) {
        super(context, recyclerView, R.layout.item_emp);
        employees.add(new Employee("Bob Right","Cleaner","Cleaning", "address", "Jim Wrong", "17.01.20", "..."));
    }

    public class Employee implements VisibilityList.Searchable {
        String name;
        String title;
        String department;
        String office;
        String superior;
        String startDate;
        String endDate;

        public Employee(String name, String title, String department, String office, String superior, String startDate, String endDate) {
            this.name = name;
            this.title = title;
            this.department = department;
            this.office = office;
            this.superior = superior;
            this.startDate = startDate;
            this.endDate = endDate;
        }

        @Override
        public boolean match(String query) {
            return name.toLowerCase().contains(query);
        }
    }

    private class EmpViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView title;
        TextView department;
        TextView office;
        TextView superior;
        TextView startDate;
        TextView endDate;

        public EmpViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.emp_name);
            title = itemView.findViewById(R.id.emp_title);
            department = itemView.findViewById(R.id.emp_department);
            office = itemView.findViewById(R.id.emp_address);
            superior = itemView.findViewById(R.id.emp_superior);
            startDate = itemView.findViewById(R.id.emp_start);
            endDate = itemView.findViewById(R.id.emp_end);
        }
    }

    @Override
    protected RecyclerView.ViewHolder onCreateCustomViewHolder(View view) {
        return new EmpViewHolder(view);
    }

    @Override
    public VisibilityList getData() {
        return employees;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onBindSwipeViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        Employee emp = employees.get(i);
        EmpViewHolder holder = (EmpViewHolder) viewHolder;
        holder.name.setText(emp.name);
        holder.title.setText(emp.title);
        holder.department.setText(emp.department);
        holder.office.setText(emp.office);
        holder.superior.setText(emp.superior);
        holder.startDate.setText(emp.startDate);
        holder.endDate.setText(emp.endDate);
    }
}
