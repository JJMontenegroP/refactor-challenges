# Tenemos un archivo de logs que contiene errores, warnings y mensajes de información.
# Cada linea del archivo comienza con una letra que indica el tipo de log. Si
# comienza con 'E' es un error, si comienza con 'W' es un warning y si comienza
# con 'I' es de información. Luego de la letra, se encuentra un entero que
# indica el tiempo del log, y luego el mensaje del log. Excepto en el caso de
# los errores, que luego del tipo se encuentra un entero que indica la severidad
# del error. Por ejemplo:

# I 147 iniciando el programa
# W 604 this is not a warning
# E 2 4562 unexpected token

# Queremos separar los errores mas severos (con severidad mayor a 50) y ordenarlos
# cronologicamente. Despues, queremos imprimirlos a la pantalla.

import os
from typing import List

# Constants
ERROR_LOG_PATH = './data/error.log'
SEVERITY_THRESHOLD = 50

class LogEntry:
    """Class representing a log entry."""
    def __init__(self, log_type: str, time: int, message: str, severity: int = None):
        self.log_type = log_type
        self.time = time
        self.message = message
        self.severity = severity

    def __str__(self):
        if self.severity is not None:
            return f"{self.log_type} {self.severity} {self.time} {self.message}"
        return f"{self.log_type} {self.time} {self.message}"

def parse_log_line(line: str) -> LogEntry:
    """Parse a line of the log file and return a LogEntry object."""
    parts = line.strip().split(' ')
    log_type = parts[0]
    if log_type == 'E':
        severity = int(parts[1])
        time = int(parts[2])
        message = ' '.join(parts[3:])
        return LogEntry(log_type, time, message, severity)
    else:
        time = int(parts[1])
        message = ' '.join(parts[2:])
        return LogEntry(log_type, time, message)

def filter_severe_errors(entries: List[LogEntry], severity_threshold: int) -> List[LogEntry]:
    """Filter for severe errors above a given severity threshold."""
    return [entry for entry in entries if entry.log_type == 'E' and entry.severity > severity_threshold]

def sort_logs_chronologically(entries: List[LogEntry]) -> List[LogEntry]:
    """Sort log entries chronologically by time."""
    return sorted(entries, key=lambda entry: entry.time)

def process_logs(file_path: str, severity_threshold: int):
    """Process the log file, filter severe errors, sort, and print them."""
    severe_errors = []

    with open(file_path, 'r') as f:
        for line in f:
            entry = parse_log_line(line)
            if entry.log_type == 'E' and entry.severity > severity_threshold:
                severe_errors.append(entry)

    # Sort errors by time
    sorted_errors = sort_logs_chronologically(severe_errors)

    # Print the sorted severe errors
    for error in sorted_errors:
        print(error)

def main():
    process_logs(ERROR_LOG_PATH, SEVERITY_THRESHOLD)

if __name__ == '__main__':
    main()
