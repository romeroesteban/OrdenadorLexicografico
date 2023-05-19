Introducción a Ciencias de la Computación
=========================================

Proyecto2: Ordenador lexicográfico
-------------------------------------------------------

### Fecha: 18 de noviembre, 2022

### Línea de comandos

Imprimir en terminal un sólo archivo ordenado

```
$ java -jar target/proyecto2.jar hombres.txt
```

Imprimir en terminal varios archivos ordenados

```
$ java -jar target/proyecto2.jar hombres.txt ejemplo.txt
```

Imprimir en terminal en orden inverso el archivo ordenado

```
$ java -jar target/proyecto2.jar hombres.txt -r
```

Imprimir en terminal y guardar en un archivo, con el nombre del identificador dado, el archivo ordenado

```
$ java -jar target/proyecto2.jar hombres.txt -o identificador.txt
```

Nota: las banderas [-r | -o <identificador>] pueden ir en cualquier orden (tanto antes como después de los archivos a ordenar), pero no pueden repetirse. Además el aegumento siguiente a la bandera -o siempre será tomado como el identificador del archivo a guardar.

### Entrada estándar

Imprimir en terminal un sólo archivo ordenado

```
$ cat hombres.txt | java -jar target/proyecto2.jar
```

Imprimir en terminal varios archivos ordenados

```
$ cat hombres.txt otro.txt | java -jar target/proyecto2.jar
```
Imprimir en terminal en orden inverso el archivo ordenado

```
$ cat hombres.txt | java -jar target/proyecto2.jar -r
```

Imprimir en terminal y guardar en un archivo, con el nombre del identificador dado, el archivo ordenado

```
$ cat hombres.txt | java -jar target/proyecto2.jar -o identificador.txt
```

Nota: si se ingresan archivos tanto en la entrada estándar como en la línea de comandos solo se tomarán en cuenta estos últimos (se ignorará la entrada estándar)
