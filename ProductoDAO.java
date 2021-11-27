package Test;

import datos.ProductoDAO;
import domain.Producto;
import domain.Producto;
import java.util.List;
import java.util.Scanner;

public class ProductoCRUD {
    
     public static void InsertData(){
        Line();
        String nombre;
        ProductoDAO productoDAO = new ProductoDAO();
        System.out.println(">>> INSERTAR UN DATO <<<");
        Producto InsertProducto = new Producto();
        Scanner leer = new Scanner(System.in);
        System.out.println("Nombre:>");
        InsertProducto.setproducto(nombre = leer.nextLine());
        ProductoDAO.Insert(InsertProducto);
        Line();
    }
    
      public static void ExtraerData(){
     
        ProductoDAO productoDAO = new ProductoDAO();
        
        List<Producto> producto = productoDAO.select();
        Line();
        System.out.println("\t\t\t\t\tBASE DE DATOS\n");
        for(Producto Producto:producto){
            System.out.println(Producto);
        }
        Line();
    }
      
    public static void DeleteData(){
        Line();
        int id = 0, res=0;
        Scanner leer = new Scanner(System.in);
        ProductoDAO productoDAO = new ProductoDAO();
        Producto DeleteProducto = new Producto();
        System.out.println("\tBORRAR DATOS");
        id = EvalueData();
        if(id == 0){
            System.out.println(">>> Salio de la opcion!");
        }
        else{
            do{
                System.out.println("Realmente desea eliminar los datos?:>");
                System.out.println("1-SI");
                System.out.println("2-No");
                res = leer.nextInt();
                if(res<1 || res>2){
                    System.out.println("Opcion no valida!...");
                }
            } while (res<1 || res>2);
            if(res==1){
                DeleteProducto.setIdProducto(id);
                productoDAO.Delete(DeleteProducto);
            }
            else{
                System.out.println(">>> Operacion cancelada!");
            }
        }
        Line();
    }
    
    public static void UpdateData(){
        Line();
        int id = 0;
        String nombre;
        System.out.println("\t\tModificar datos");
        ProductoDAO productoDAO = new ProductoDAO();
        Producto UpdateProducto = new Producto();
        //System.out.println(">>> MODIFICANDO DATOS <<<");
        Scanner leer = new Scanner(System.in);
        id = EvalueData();
        if(id == 0){
            System.out.println(">>> Salio de la opcion!");
        }
        else{
            System.out.println("Nuevo Nombre Producto:>");
            UpdateProducto.setproducto(nombre = leer.nextLine());
            UpdateProducto.setIdProducto(id);
            productoDAO.Update(UpdateProducto);
        }
        Line();
    }
    
       public static int EvalueData(){
        int enc = 0, id = 0, pos=0;
        Scanner leer = new Scanner(System.in);
        ProductoDAO productoDAO = new ProductoDAO();
        List<Producto> productosDAO = productoDAO.select();
        do{
            id=0;
            pos=0;
            Line();
            //System.out.println(">>> MODIFICANDO DATOS <<<");
            System.out.println("Ingrese el identificador (id), para salir -> '0':");
            System.out.println(":>");
            id = leer.nextInt();
            if(id == 0){break;}
            System.out.println("\t\t\t\t\tBuscando identificador...\n");
            for(Producto producto:productosDAO){
                if(id != producto.getIdProducto()){
                    enc  = 0;
                }
                else{
                    enc = 1;
                    break;
                }
                pos++;
            }
            if(id<0){
                System.out.println(">>> Error: No existen identificadores negativos! " + "->" + " ["+ id +"]");
            }
            else if(enc == 0){
                System.out.println("Este identificador [" + id + "] no existe en la base de datos o fue eliminado!");
            }
            else if(enc == 1){
                System.out.println("*** ID = [" + id + "] Encontrado! ***");
                System.out.println("Datos:\n" + productosDAO.get(pos));
            }
            Line();
        }while(enc != 1 || id == 0);
        return id;
    }
    
    public static void Line(){
        System.out.println("\n------------------------------------------------------------------------------------------------------------");
    }
}
