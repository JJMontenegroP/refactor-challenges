import os

FILE_PATH = "./refactoring/problema4/data/todo.txt"

def read_todo_list(file_path):
    """Reads the to-do list from a file and returns a list of tasks."""
    if not os.path.exists(file_path):
        print('No existe el archivo todo.txt')
        return []
    
    with open(file_path, 'r') as file:
        return [line.strip() for line in file.readlines()]

def write_todo_list(file_path, tasks):
    """Writes the current list of tasks to the file."""
    with open(file_path, 'w') as file:
        for task in tasks:
            file.write(task + '\n')

def display_tasks(tasks):
    """Displays all tasks."""
    print('-------------------------')
    print('Tareas:')
    for i, task in enumerate(tasks, start=1):
        print(f'{i}. {task}')

def add_task(tasks):
    """Prompts the user to add a new task."""
    task = input('Ingrese la tarea: ')
    tasks.append(task)
    print('Tarea agregada')

def delete_task(tasks):
    """Prompts the user to delete a task by its number."""
    try:
        task_number = int(input('Ingrese el número de la tarea a eliminar: '))
        if 1 <= task_number <= len(tasks):
            removed_task = tasks.pop(task_number - 1)
            print(f'Tarea "{removed_task}" eliminada')
        else:
            print('Número de tarea inválido')
    except ValueError:
        print('Entrada inválida, por favor ingrese un número')

def main_menu():
    """Displays the main menu and handles user interactions."""
    tasks = read_todo_list(FILE_PATH)
    print('Bienvenido a su lista de tareas. Estas son sus opciones:')

    while True:
        print('-------------------------')
        print('1. Ver tareas')
        print('2. Agregar una tarea')
        print('3. Eliminar una tarea')
        print('4. Salir')
        print('-------------------------')

        option = input('Ingrese una opción: ')

        if option == '1':
            display_tasks(tasks)
        elif option == '2':
            add_task(tasks)
        elif option == '3':
            delete_task(tasks)
        elif option == '4':
            write_todo_list(FILE_PATH, tasks)
            print('Adios')
            break
        else:
            print('Opción no válida. Intente de nuevo.')

if __name__ == '__main__':
    main_menu()
