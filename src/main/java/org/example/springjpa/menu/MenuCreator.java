package org.example.springjpa.menu;

import ch.qos.logback.core.encoder.EchoEncoder;
import org.example.springjpa.models.Department;
import org.example.springjpa.models.Organisation;
import org.example.springjpa.models.Staff;
import org.example.springjpa.services.DepartmentService;
import org.example.springjpa.services.OrganizationService;
import org.example.springjpa.services.StaffService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MenuCreator {
    final
    DepartmentService departmentService;
    final
    OrganizationService organizationService;
    final
    StaffService staffService;


    public MenuCreator(DepartmentService departmentService, OrganizationService organizationService, StaffService staffService) {
        this.departmentService = departmentService;
        this.organizationService = organizationService;
        this.staffService = staffService;
    }


    public Menu createMenu() {
        MenuItem mainMenu = new MenuItem(0, "", null, null);

        MenuItem department = new MenuItem(1, "department", null, null);
        MenuItem staff = new MenuItem(2, "staff", null, null);
        MenuItem organisation = new MenuItem(3, "organisation", null, null);

        department.addChildItem(1, "Add", department, new FunctionCaller() {
            @Override
            public MenuItem func() {
                Scanner scanner = new Scanner(System.in);

                System.out.println("==== Create ====");
                System.out.print("Input Department name: ");
                String name = scanner.next();

                System.out.print("Input Department employee amount: ");
                int employeeAmount = scanner.nextInt();

                System.out.print("Input Department rooms: ");
                String rooms = scanner.next();

                System.out.print("Input ID CEO: ");
                int staff_id;
                Staff ceo = null;
                try{
                    staff_id = Integer.parseInt(scanner.next());
                    ceo = staffService.findById(staff_id);
                }catch(NumberFormatException e){
                    System.out.println(e.getMessage());
                }


                try{
                    departmentService.save(new Department(name, employeeAmount, rooms, ceo));
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
                return department;
            }
        });

        department.addChildItem(2, "Find All", department, new FunctionCaller() {
            public MenuItem func() {
                departmentService.findAll();
                return department;
            }
        });

        department.addChildItem(3, "Find by ID", department, new FunctionCaller() {
            public MenuItem func() {
                Scanner scanner = new Scanner(System.in);

                System.out.println("==== Find by ID ====");
                System.out.print("Введите ID: ");
                int id = Integer.parseInt(scanner.next());
                try{
                    if (departmentService.findById(id) == null){
                        throw new Exception("Department with ID " + id + " not found");
                    };
                }catch(Exception e){
                    System.out.println(e.getMessage());
                    return department;
                }
                return department;
            }
        });

        department.addChildItem(4, "Update", department, new FunctionCaller() {
            public MenuItem func() {
                Scanner scanner = new Scanner(System.in);
                Department departmentUpdatable;
                System.out.println("==== Update ====");
                System.out.print("Input id: ");
                int id = scanner.nextInt();
                try {
                    departmentUpdatable = departmentService.findById(id);
                    if (departmentUpdatable == null) {
                        throw new Exception("Department with ID " + id + " not found");
                    }
                }catch (Exception e) {
                    System.out.println(e.getMessage());
                    return department;
                }
                    System.out.print("Input name: ");
                    scanner.nextLine();
                    String name = scanner.nextLine();

                    System.out.print("Input employee amount: ");
                    int employeeAmount = Integer.parseInt(scanner.next());

                    System.out.print("Input rooms: ");
                    String rooms = scanner.next();

                    System.out.print("Input ceo id: ");
                    int ceo_id = Integer.parseInt(scanner.next());

                    Staff ceo = staffService.findById(ceo_id);

                    departmentUpdatable.setName(name);
                    departmentUpdatable.setAmountStaff(employeeAmount);
                    departmentUpdatable.setRooms(rooms);
                    departmentUpdatable.setCeo(ceo);

                    departmentService.save(departmentUpdatable);

                return department;

            }
        });

        department.addChildItem(5, "Delete", department, new FunctionCaller() {
            public MenuItem func() {
                Scanner scanner = new Scanner(System.in);
                System.out.println("==== Delete ====");
                System.out.print("Input id: ");
                int id = Integer.parseInt(scanner.next());
                departmentService.delete(id);
                return department;
            }
        });

        department.addChildItem(6, "back", department, new FunctionCaller() {
            public MenuItem func() {
                return mainMenu;
            }
        });

        staff.addChildItem(1, "Add", staff, new FunctionCaller() {
            @Override
            public MenuItem func() {
                Scanner scanner = new Scanner(System.in);
                System.out.println("==== Create ====");
                System.out.print("Input name: ");
                String staff_name = scanner.nextLine();

                System.out.print("Input staff address: ");
                String staff_address = scanner.nextLine();

                System.out.print("Input staff age: ");
                int staff_age = Integer.parseInt(scanner.next());

                System.out.print("Input staff position: ");
                scanner.nextLine();
                String staff_position = scanner.nextLine();

                System.out.print("Input staff department: ");
                int staff_department_id;
                Department department = null;
                try{
                    staff_department_id = Integer.parseInt(scanner.next());
                    department = departmentService.findById(staff_department_id);
                }catch(NumberFormatException e){
                    System.out.println(e.getMessage());
                }


                staffService.save(new Staff(staff_name, staff_address, staff_age, staff_position, department));
                return staff;
            }
        });

        staff.addChildItem(2, "Read All", staff, new FunctionCaller() {
            public MenuItem func() {
                staffService.findAll();
                return staff;
            }
        });

        staff.addChildItem(3, "Read by ID", staff, new FunctionCaller() {
            public MenuItem func() {
                Scanner scanner = new Scanner(System.in);
                System.out.println("==== Read by ID ====");
                System.out.println("Input id: ");
                int id = Integer.parseInt(scanner.next());
                try{
                    if(staffService.findById(id) == null){
                        throw new Exception("Staff with ID " + id + " not found");
                    };
                }catch(Exception e){
                    System.out.println(e.getMessage());
                    return staff;
                }
                return staff;
            }
        });

        staff.addChildItem(4, "Update", staff, new FunctionCaller() {
            public MenuItem func() {
                Scanner scanner = new Scanner(System.in);
                Staff staffUpdatable;
                System.out.println("==== Update ====");
                System.out.print("Input id: ");
                int id = Integer.parseInt(scanner.next());

                try{
                    staffUpdatable = staffService.findById(id);
                    if(staffUpdatable == null){
                        throw new Exception("Staff with ID " + id + " not found");
                    }
                }catch (Exception e){
                    System.out.println(e.getMessage());
                    return staff;
                }

                System.out.print("Input staff name: ");
                scanner.nextLine();
                String staff_name = scanner.nextLine();

                System.out.print("Input staff address: ");
                String staff_address = scanner.nextLine();

                System.out.print("Input staff age: ");
                int staff_age = Integer.parseInt(scanner.next());

                System.out.print("Input staff position: ");
                scanner.nextLine();
                String staff_position = scanner.nextLine();

                System.out.print("Input staff department: ");
                int staff_department_id = Integer.parseInt(scanner.next());

                Department department = departmentService.findById(staff_department_id);

                staffUpdatable.setName(staff_name);
                staffUpdatable.setAddress(staff_address);
                staffUpdatable.setAge(staff_age);
                staffUpdatable.setPosition(staff_position);
                staffUpdatable.setDepartment(department);

                staffService.save(staffUpdatable);

                return staff;
            }
        });

        staff.addChildItem(5, "Delete", staff, new FunctionCaller() {
            public MenuItem func() {
                Scanner scanner = new Scanner(System.in);
                System.out.println("==== Delete ====");
                System.out.print("Input id: ");
                int id = Integer.parseInt(scanner.next());
                staffService.delete(id);
                return staff;
            }
        });

        staff.addChildItem(6, "back", staff, new FunctionCaller() {
            public MenuItem func() {
                return mainMenu;
            }
        });

        organisation.addChildItem(1, "Add", organisation, new FunctionCaller() {
            @Override
            public MenuItem func() {
                Scanner scanner = new Scanner(System.in);
                System.out.println("==== Create ====");
                System.out.print("Input name: ");
                String organisation_name = scanner.nextLine();
                System.out.print("Input organisation address: ");
                String organisation_address = scanner.nextLine();
                System.out.print("Input organisation ceo: ");
                int organisation_ceo_id;
                Staff organisationCeo = null;

                try{
                    organisation_ceo_id = Integer.parseInt(scanner.nextLine());
                    organisationCeo = staffService.findById(organisation_ceo_id);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
                try{
                    organizationService.save(new Organisation(organisation_name, organisation_address, organisationCeo));
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
                return organisation;
            }
        });

        organisation.addChildItem(2, "Read All", organisation, new FunctionCaller() {
            public MenuItem func() {
                organizationService.findAll();
                return organisation;
            }
        });

        organisation.addChildItem(3, "Read by ID", organisation, new FunctionCaller() {
            public MenuItem func() {
                Scanner scanner = new Scanner(System.in);
                System.out.println("==== Read by ID ====");
                System.out.print("Input id: ");
                int id = Integer.parseInt(scanner.next());

                try{
                    if(organizationService.findById(id) == null){
                        throw new Exception("Organization with ID " + id + " not found");
                    }
                }catch (Exception e){
                    System.out.println(e.getMessage());
                    return organisation;
                }

                return organisation;
            }
        });

        organisation.addChildItem(4, "Update", organisation, new FunctionCaller() {
            public MenuItem func() {
                Scanner scanner = new Scanner(System.in);
                Organisation organisationUpdatable;
                System.out.println("==== Update ====");
                System.out.print("Input id: ");
                int id = Integer.parseInt(scanner.next());

                try{
                    organisationUpdatable = organizationService.findById(id);
                    if(organisationUpdatable == null){
                        throw new Exception("Organization with ID " + id + " not found");
                    }
                }catch (Exception e){
                    System.out.println(e.getMessage());
                    return organisation;
                }

                System.out.print("Input organisation name: ");
                scanner.nextLine();
                String organisation_name = scanner.nextLine();

                System.out.print("Input organisation address: ");
                String organisation_address = scanner.nextLine();

                System.out.print("Input organisation ceo: ");
                int organisation_ceo_id = Integer.parseInt(scanner.next());

                Staff organisationCeo = staffService.findById(organisation_ceo_id);

                organisationUpdatable.setName(organisation_name);
                organisationUpdatable.setAddress(organisation_address);
                organisationUpdatable.setCeo(organisationCeo);

                organizationService.save(organisationUpdatable);
                return organisation;
            }
        });

        organisation.addChildItem(5, "Delete", organisation, new FunctionCaller() {
            public MenuItem func() {
                Scanner scanner = new Scanner(System.in);
                System.out.println("==== Delete ====");
                System.out.print("Input id: ");
                int id = Integer.parseInt(scanner.next());
                organizationService.delete(id);
                return organisation;
            }
        });

        organisation.addChildItem(6, "back", organisation, new FunctionCaller() {
            public MenuItem func() {
                return mainMenu;
            }
        });

        MenuItem exit = new MenuItem(4, "exit", null, new FunctionCaller() {
            public MenuItem func() {
                return null;
            }
        });
        mainMenu.addChildItem(department);
        mainMenu.addChildItem(staff);
        mainMenu.addChildItem(organisation);
        mainMenu.addChildItem(exit);

        return new Menu(mainMenu.getChildrenItems());
    }

}
