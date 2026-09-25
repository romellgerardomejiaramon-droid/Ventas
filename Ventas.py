import random
import time


class Ventas:

    def __init__(self):
        self.meses = [
            "Enero",
            "Febrero",
            "Marzo",
            "Abril",
            "Mayo",
            "Junio",
            "Julio",
            "Agosto",
            "Septiembre",
            "Octubre",
            "Noviembre",
            "Diciembre",
        ]
        self.departamentos = ["Ropa", "Deportes", "Jugueteria"]
        self.matriz_ventas = [[] for _ in range(12)]
        self.generar_ventas_aleatorias()

    def generar_ventas_aleatorias(self):
        for i in range(12):
            self.matriz_ventas[i].clear()
            for _ in range(len(self.departamentos)):
                monto = random.uniform(1000, 20000)
                self.matriz_ventas[i].append(round(monto, 2))

    def agregar_departamento(self, nombre_depto):
        self.departamentos.append(nombre_depto)
        for i in range(12):
            self.matriz_ventas[i].append(0.0)
        print(f"\nDepartamento '{nombre_depto}' agregado a la lista.")

    def insertar_venta(self, mes_num, depto_num, monto):
        if (
            1 <= mes_num <= 12
            and 1 <= depto_num <= len(self.departamentos)
        ):
            self.matriz_ventas[mes_num - 1][depto_num - 1] = monto
            print(
                f"\nVenta de ${monto:.2f} registrada en"
                f" {self.departamentos[depto_num - 1]} ({self.meses[mes_num - 1]})."
            )
        else:
            print("\nError: Numero de mes o departamento invalido.")

    def insertar_venta_silenciosa(self, mes_num, depto_num, monto):
        if (
            1 <= mes_num <= 12
            and 1 <= depto_num <= len(self.departamentos)
        ):
            self.matriz_ventas[mes_num - 1][depto_num - 1] = monto

    def buscar_venta(self, mes_num, depto_num):
        if (
            1 <= mes_num <= 12
            and 1 <= depto_num <= len(self.departamentos)
        ):
            monto = self.matriz_ventas[mes_num - 1][depto_num - 1]
            print(
                f"\nVenta en {self.departamentos[depto_num - 1]}"
                f" ({self.meses[mes_num - 1]}): ${monto:.2f}"
            )
            return monto
        else:
            print("\nError: Numero de mes o departamento invalido.")
            return -1

    def eliminar_venta(self, mes_num, depto_num):
        if (
            1 <= mes_num <= 12
            and 1 <= depto_num <= len(self.departamentos)
        ):
            self.matriz_ventas[mes_num - 1][depto_num - 1] = 0.0
            print(
                f"\nVenta eliminada (reiniciada a $0.00) en"
                f" {self.departamentos[depto_num - 1]} ({self.meses[mes_num - 1]})."
            )
        else:
            print("\nError: Numero de mes o departamento invalido.")

    def mostrar_tabla(self):
        print("\n------------------- TABLA DE VENTAS -------------------")
        header = f"{'Mes':<12} | " + "".join(
            [f"{dept:<12} | " for dept in self.departamentos]
        )
        print(header)
        print("-" * len(header))

        totales_anuales = [0.0] * len(self.departamentos)

        for i in range(12):
            fila = f"{self.meses[i]:<12} | "
            for j in range(len(self.departamentos)):
                venta = self.matriz_ventas[i][j]
                totales_anuales[j] += venta
                fila += f"${venta:<11.2f} | "
            print(fila)

        print("-" * len(header))
        fila_total = f"{'TOTAL ANUAL':<12} | " + "".join(
            [f"${tot:<11.2f} | " for tot in totales_anuales]
        )
        print(fila_total)
        print("-" * len(header))

    def mostrar_departamentos(self):
        print("\nDepartamentos disponibles:")
        for i, dept in enumerate(self.departamentos, start=1):
            print(f"{i}. {dept}")

    def get_cantidad_departamentos(self):
        return len(self.departamentos)

    def get_nombre_mes(self, mes_num):
        return self.meses[mes_num - 1]


def main():
    inicio_arranque = time.time()

    sistema = Ventas()

    fin_arranque = time.time()
    segundos_arranque = fin_arranque - inicio_arranque
    ms_arranque = int(segundos_arranque * 1000)
    print(
        f"Tiempo de carga e inicializacion del programa:"
        f" {segundos_arranque:.3f} segundos ({ms_arranque} ms)"
    )

    opcion = 0

    while opcion != 7:
        print("\n====================================")
        print("    SISTEMA DE CONTROL DE VENTAS    ")
        print("====================================")
        print("1. Ver tabla de ventas")
        print("2. Insertar / Modificar venta")
        print("3. Buscar venta")
        print("4. Eliminar venta")
        print("5. Agregar nuevo departamento")
        print("6. Regenerar datos aleatorios")
        print("7. Salir")

        try:
            opcion = int(input("Selecciona una opcion (1-7): "))
        except ValueError:
            print("\nError: Opcion no valida. Por favor ingresa un numero.")
            continue

        inicio_tiempo = time.time()

        if opcion == 1:
            sistema.mostrar_tabla()

        elif opcion == 2:
            try:
                m = int(input("Ingrese numero de mes (1-12): "))
                sistema.mostrar_departamentos()
                d = int(input("Ingrese numero de departamento: "))
                monto = float(input("Ingrese el monto: "))
                sistema.insertar_venta(m, d, monto)
            except ValueError:
                print("\nError: Entrada numerica invalida.")

        elif opcion == 3:
            try:
                m = int(input("Ingrese numero de mes (1-12): "))
                sistema.mostrar_departamentos()
                d = int(input("Ingrese numero de departamento: "))
                sistema.buscar_venta(m, d)
            except ValueError:
                print("\nError: Entrada numerica invalida.")

        elif opcion == 4:
            try:
                m = int(input("Ingrese numero de mes (1-12): "))
                sistema.mostrar_departamentos()
                d = int(input("Ingrese numero de departamento: "))
                sistema.eliminar_venta(m, d)
            except ValueError:
                print("\nError: Entrada numerica invalida.")

        elif opcion == 5:
            nombre_depto = input("\nIngrese el nombre del nuevo departamento: ")
            sistema.agregar_departamento(nombre_depto)

            print(
                f"\nQue desea hacer con los datos de venta de '{nombre_depto}'?"
            )
            print("1. Mantener todas las ventas en $0.00")
            print("2. Ingresar los montos mes a mes manualmente")

            try:
                opcion_llenado = int(input("Seleccione una opcion (1 o 2): "))
            except ValueError:
                opcion_llenado = 1

            if opcion_llenado == 2:
                nuevo_depto_id = sistema.get_cantidad_departamentos()
                print(f"\n--- Ingresando ventas para: {nombre_depto} ---")

                for m in range(1, 13):
                    while True:
                        try:
                            monto = float(
                                input(
                                    f"Monto para {sistema.get_nombre_mes(m)}: $"
                                )
                            )
                            sistema.insertar_venta_silenciosa(
                                m, nuevo_depto_id, monto
                            )
                            break
                        except ValueError:
                            print(
                                "Error: Ingrese un valor numerico valido para"
                                " el monto."
                            )

                print(
                    f"\nTodas las ventas de '{nombre_depto}' han sido"
                    " registradas exitosamente."
                )
            else:
                print(
                    f"Las ventas de '{nombre_depto}' se han inicializado en"
                    " $0.00."
                )

        elif opcion == 6:
            sistema.generar_ventas_aleatorias()
            print("\nNuevas ventas aleatorias generadas.")

        elif opcion == 7:
            print("\nHasta luego!")

        fin_tiempo = time.time()
        segundos = fin_tiempo - inicio_tiempo
        milisegundos = int(segundos * 1000)

        if 1 <= opcion <= 7:
            print(
                f"\nTiempo transcurrido para procesar la opcion {opcion}:"
                f" {segundos:.3f} segundos ({milisegundos} ms)"
            )


if __name__ == "__main__":
    main()
