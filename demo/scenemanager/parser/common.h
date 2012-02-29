#ifndef _COMMON_H_
#define _COMMON_H_

#include "parser.h"

#ifdef __cplusplus
extern "C" {
#endif
    void push(FileInfo);
    FileInfo pop();
    void open_input_file();
#ifdef __cplusplus
}
#endif

#endif
