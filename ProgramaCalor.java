import java.util.*;

class Material {
    private String nombre;
    private double calorEspecifico; 

    public Material(String nombre, double calorEspecifico) {
        this.nombre = nombre;
        this.calorEspecifico = calorEspecifico;
    }

    public String getNombre() { 
        return nombre; 
    }
    public double getCalorEspecifico() { 
        return calorEspecifico; 
    }
}

class Unidad {
    public static double convertirMasa(double valor, String unidad) {
        unidad = unidad.toLowerCase();
        switch (unidad) {
            case "g": return valor / 1000;
            case "lb": return valor * 0.453592;
            case "oz": return valor * 0.0283495;
            default: return valor; // kg
        }
    }

    public static double convertirEnergia(double valor, String unidad) {
        unidad = unidad.toLowerCase();
        switch (unidad) {
            case "kj": return valor * 1000;
            case "cal": return valor * 4.184;
            case "kcal": return valor * 4184;
            default: return valor; // J
        }
    }

    public static double convertirTemperatura(double valor, String unidad) {
        unidad = unidad.toLowerCase();
        switch (unidad) {
            case "k": return valor - 273.15;
            case "f": return (valor - 32) * 5/9;
            default: return valor; // °C
        }
    }
}

class Calor {
    public static double calcularQ(double m, double c, double Ti, double Tf) {
        return m * c * (Tf - Ti);
    }

    public static double calcularM(double Q, double c, double dT) {
        return Q / (c * dT);
    }

    public static double calcularC(double Q, double m, double dT) {
        return Q / (m * dT);
    }

    public static double calcularDeltaT(double Q, double m, double c) {
        return Q / (m * c);
    }

    public static double temperaturaFinal1(double Q, double m, double c, double Ti) {
        return Ti + (Q / (m * c));
    }

    public static double mezcla(double m1, double c1, double T1, double m2, double c2, double T2) {
        return (m1*c1*T1 + m2*c2*T2) / (m1*c1 + m2*c2);
    }
}

public class ProgramaCalor {
    static ArrayList<Material> materiales = new ArrayList<>();

    public static void cargarMateriales() {
        materiales.add(new Material("Agua", 4186));
        materiales.add(new Material("Aire", 1005));
        materiales.add(new Material("Aluminio", 900));
        materiales.add(new Material("Cobre", 385));
        materiales.add(new Material("Acero", 500));
        materiales.add(new Material("Hielo", 2100));
        materiales.add(new Material("Plata", 235));
        materiales.add(new Material("Oro", 129));
        materiales.add(new Material("Vidrio", 730));
        materiales.add(new Material("Latón", 380));
        materiales.add(new Material("Madera", 1700));
        materiales.add(new Material("Plástico", 1300));
        materiales.add(new Material("Plomo", 128));
        materiales.add(new Material("Mercurio", 140));
        materiales.add(new Material("Titanio", 520));
        materiales.add(new Material("Nitrógeno", 1040));
        materiales.add(new Material("Oxígeno", 918));
        materiales.add(new Material("Concreto", 880));
        materiales.add(new Material("Aceite", 2000));
        materiales.add(new Material("Arena", 830));
    }

    public static Material budmarMaterial(String nombre) {
        for (Material m : materiales) {
            if (m.getNombre().equalsIgnoreCase(nombre)) return m;
        }
        return null;
    }

    public static void main(String[] args) {
        cargarMateriales();
        Scanner dm = new Scanner(System.in);

        while (true) {
            System.out.println("===========================");
            System.out.println(" CALCULO DE CALOR (Q=mcΔT)");
            System.out.println("===========================");
            System.out.println("1. Calcular Q");
            System.out.println("2. Calcular masa m");
            System.out.println("3. Calcular calor específico c");
            System.out.println("4. Calcular ΔT");
            System.out.println("5. Temperatura final (1 material)");
            System.out.println("6. Mezcla de 2 materiales");
            System.out.println("7. Salir");
            System.out.print("Opción: ");

            int op = dm.nextInt(); dm.nextLine();

            if (op == 7) break;

            switch (op) {
                case 1: 
                    System.out.print("Material: ");
                    String mat = dm.nextLine();
                    Material m = budmarMaterial(mat);
                    if (m == null) { 
                        System.out.println("Material no encontrado"); 
                        break; 
                    }

                    System.out.print("Masa: "); double masa = dm.nextDouble();
                    System.out.print("Unidad (kg, g, lb, oz): "); 
                    String um = dm.next();
                    masa = Unidad.convertirMasa(masa, um);

                    System.out.print("Temperatura inicial: "); 
                    double Ti = dm.nextDouble();
                    System.out.print("Unidad (°C, K, F): "); 
                    String ui = dm.next();
                    Ti = Unidad.convertirTemperatura(Ti, ui);

                    System.out.print("Temperatura final: "); 
                    double Tf = dm.nextDouble();
                    System.out.print("Unidad (°C, K, F): "); 
                    String uf = dm.next();
                    Tf = Unidad.convertirTemperatura(Tf, uf);

                    double Q = Calor.calcularQ(masa, m.getCalorEspecifico(), Ti, Tf);
                    System.out.println("Q = " + Q + " J");
                    break;

                case 6: 
                    System.out.println("Material 1: ");
                    Material m1 = budmarMaterial(dm.nextLine());
                    if (m1 == null) { System.out.println("No existe"); break; }
                    System.out.print("Masa: "); 
                    double ma1 = dm.nextDouble();
                    System.out.print("Unidad: "); 
                    ma1 = Unidad.convertirMasa(ma1, dm.next());
                    System.out.print("Temperatura: "); 
                    double T1 = dm.nextDouble();
                    System.out.print("Unidad: "); T1 = 
                    Unidad.convertirTemperatura(T1, dm.next());
                    dm.nextLine();

                    System.out.println("Material 2: ");
                    Material m2 = budmarMaterial(dm.nextLine());
                    if (m2 == null) { System.out.println("No existe"); break; }
                    System.out.print("Masa: "); double ma2 = dm.nextDouble();
                    System.out.print("Unidad: "); ma2 = Unidad.convertirMasa(ma2, dm.next());
                    System.out.print("Temperatura: "); double T2 = dm.nextDouble();
                    System.out.print("Unidad: "); T2 = Unidad.convertirTemperatura(T2, dm.next());

                    double TfMix = Calor.mezcla(ma1, m1.getCalorEspecifico(), T1,
                                                 ma2, m2.getCalorEspecifico(), T2);

                    System.out.println("Temperatura final de mezcla = " + TfMix + " °C");
                    break;
            }
        dm.close();
        }
    }
}
