##
## Auto Generated makefile by CodeLite IDE
## any manual changes will be erased      
##
## Debug
ProjectName            :=PEC2
ConfigurationName      :=Debug
WorkspacePath          :=/Users/mr.blissfulgrin/Documents/UAH_2017_2018/DATOS/PEC2/PEC2
ProjectPath            :=/Users/mr.blissfulgrin/Documents/UAH_2017_2018/DATOS/PEC2/PEC2/PEC2
IntermediateDirectory  :=./Debug
OutDir                 := $(IntermediateDirectory)
CurrentFileName        :=
CurrentFilePath        :=
CurrentFileFullPath    :=
User                   :=Juan Casado Ballesteros
Date                   :=06/01/2019
CodeLitePath           :="/Users/mr.blissfulgrin/Library/Application Support/codelite"
LinkerName             :=/usr/bin/g++
SharedObjectLinkerName :=/usr/bin/g++ -dynamiclib -fPIC
ObjectSuffix           :=.o
DependSuffix           :=.o.d
PreprocessSuffix       :=.i
DebugSwitch            :=-g 
IncludeSwitch          :=-I
LibrarySwitch          :=-l
OutputSwitch           :=-o 
LibraryPathSwitch      :=-L
PreprocessorSwitch     :=-D
SourceSwitch           :=-c 
OutputFile             :=$(IntermediateDirectory)/$(ProjectName)
Preprocessors          :=
ObjectSwitch           :=-o 
ArchiveOutputSwitch    := 
PreprocessOnlySwitch   :=-E
ObjectsFileList        :="PEC2.txt"
PCHCompileFlags        :=
MakeDirCommand         :=mkdir -p
LinkOptions            :=  
IncludePath            :=  $(IncludeSwitch). $(IncludeSwitch). 
IncludePCH             := 
RcIncludePath          := 
Libs                   := 
ArLibs                 :=  
LibPath                := $(LibraryPathSwitch). 

##
## Common variables
## AR, CXX, CC, AS, CXXFLAGS and CFLAGS can be overriden using an environment variables
##
AR       := /usr/bin/ar rcu
CXX      := /usr/bin/g++
CC       := /usr/bin/gcc
CXXFLAGS :=  -g -O0 -Wall $(Preprocessors)
CFLAGS   :=  -g -O0 -Wall $(Preprocessors)
ASFLAGS  := 
AS       := /usr/bin/as


##
## User defined environment variables
##
CodeLiteDir:=/Applications/codelite.app/Contents/SharedSupport/
Objects0=$(IntermediateDirectory)/NodoLista.cpp$(ObjectSuffix) $(IntermediateDirectory)/NodoArbol.cpp$(ObjectSuffix) $(IntermediateDirectory)/Gestor.cpp$(ObjectSuffix) $(IntermediateDirectory)/Cola.cpp$(ObjectSuffix) $(IntermediateDirectory)/Cliente.cpp$(ObjectSuffix) $(IntermediateDirectory)/Colores.cpp$(ObjectSuffix) $(IntermediateDirectory)/NodoCola.cpp$(ObjectSuffix) $(IntermediateDirectory)/Lista.cpp$(ObjectSuffix) $(IntermediateDirectory)/main.cpp$(ObjectSuffix) $(IntermediateDirectory)/Arbol.cpp$(ObjectSuffix) \
	



Objects=$(Objects0) 

##
## Main Build Targets 
##
.PHONY: all clean PreBuild PrePreBuild PostBuild MakeIntermediateDirs
all: $(OutputFile)

$(OutputFile): $(IntermediateDirectory)/.d $(Objects) 
	@$(MakeDirCommand) $(@D)
	@echo "" > $(IntermediateDirectory)/.d
	@echo $(Objects0)  > $(ObjectsFileList)
	$(LinkerName) $(OutputSwitch)$(OutputFile) @$(ObjectsFileList) $(LibPath) $(Libs) $(LinkOptions)

MakeIntermediateDirs:
	@test -d ./Debug || $(MakeDirCommand) ./Debug


$(IntermediateDirectory)/.d:
	@test -d ./Debug || $(MakeDirCommand) ./Debug

PreBuild:


##
## Objects
##
$(IntermediateDirectory)/NodoLista.cpp$(ObjectSuffix): NodoLista.cpp $(IntermediateDirectory)/NodoLista.cpp$(DependSuffix)
	$(CXX) $(IncludePCH) $(SourceSwitch) "/Users/mr.blissfulgrin/Documents/UAH_2017_2018/DATOS/PEC2/PEC2/PEC2/NodoLista.cpp" $(CXXFLAGS) $(ObjectSwitch)$(IntermediateDirectory)/NodoLista.cpp$(ObjectSuffix) $(IncludePath)
$(IntermediateDirectory)/NodoLista.cpp$(DependSuffix): NodoLista.cpp
	@$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) -MG -MP -MT$(IntermediateDirectory)/NodoLista.cpp$(ObjectSuffix) -MF$(IntermediateDirectory)/NodoLista.cpp$(DependSuffix) -MM NodoLista.cpp

$(IntermediateDirectory)/NodoLista.cpp$(PreprocessSuffix): NodoLista.cpp
	$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) $(PreprocessOnlySwitch) $(OutputSwitch) $(IntermediateDirectory)/NodoLista.cpp$(PreprocessSuffix) NodoLista.cpp

$(IntermediateDirectory)/NodoArbol.cpp$(ObjectSuffix): NodoArbol.cpp $(IntermediateDirectory)/NodoArbol.cpp$(DependSuffix)
	$(CXX) $(IncludePCH) $(SourceSwitch) "/Users/mr.blissfulgrin/Documents/UAH_2017_2018/DATOS/PEC2/PEC2/PEC2/NodoArbol.cpp" $(CXXFLAGS) $(ObjectSwitch)$(IntermediateDirectory)/NodoArbol.cpp$(ObjectSuffix) $(IncludePath)
$(IntermediateDirectory)/NodoArbol.cpp$(DependSuffix): NodoArbol.cpp
	@$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) -MG -MP -MT$(IntermediateDirectory)/NodoArbol.cpp$(ObjectSuffix) -MF$(IntermediateDirectory)/NodoArbol.cpp$(DependSuffix) -MM NodoArbol.cpp

$(IntermediateDirectory)/NodoArbol.cpp$(PreprocessSuffix): NodoArbol.cpp
	$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) $(PreprocessOnlySwitch) $(OutputSwitch) $(IntermediateDirectory)/NodoArbol.cpp$(PreprocessSuffix) NodoArbol.cpp

$(IntermediateDirectory)/Gestor.cpp$(ObjectSuffix): Gestor.cpp $(IntermediateDirectory)/Gestor.cpp$(DependSuffix)
	$(CXX) $(IncludePCH) $(SourceSwitch) "/Users/mr.blissfulgrin/Documents/UAH_2017_2018/DATOS/PEC2/PEC2/PEC2/Gestor.cpp" $(CXXFLAGS) $(ObjectSwitch)$(IntermediateDirectory)/Gestor.cpp$(ObjectSuffix) $(IncludePath)
$(IntermediateDirectory)/Gestor.cpp$(DependSuffix): Gestor.cpp
	@$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) -MG -MP -MT$(IntermediateDirectory)/Gestor.cpp$(ObjectSuffix) -MF$(IntermediateDirectory)/Gestor.cpp$(DependSuffix) -MM Gestor.cpp

$(IntermediateDirectory)/Gestor.cpp$(PreprocessSuffix): Gestor.cpp
	$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) $(PreprocessOnlySwitch) $(OutputSwitch) $(IntermediateDirectory)/Gestor.cpp$(PreprocessSuffix) Gestor.cpp

$(IntermediateDirectory)/Cola.cpp$(ObjectSuffix): Cola.cpp $(IntermediateDirectory)/Cola.cpp$(DependSuffix)
	$(CXX) $(IncludePCH) $(SourceSwitch) "/Users/mr.blissfulgrin/Documents/UAH_2017_2018/DATOS/PEC2/PEC2/PEC2/Cola.cpp" $(CXXFLAGS) $(ObjectSwitch)$(IntermediateDirectory)/Cola.cpp$(ObjectSuffix) $(IncludePath)
$(IntermediateDirectory)/Cola.cpp$(DependSuffix): Cola.cpp
	@$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) -MG -MP -MT$(IntermediateDirectory)/Cola.cpp$(ObjectSuffix) -MF$(IntermediateDirectory)/Cola.cpp$(DependSuffix) -MM Cola.cpp

$(IntermediateDirectory)/Cola.cpp$(PreprocessSuffix): Cola.cpp
	$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) $(PreprocessOnlySwitch) $(OutputSwitch) $(IntermediateDirectory)/Cola.cpp$(PreprocessSuffix) Cola.cpp

$(IntermediateDirectory)/Cliente.cpp$(ObjectSuffix): Cliente.cpp $(IntermediateDirectory)/Cliente.cpp$(DependSuffix)
	$(CXX) $(IncludePCH) $(SourceSwitch) "/Users/mr.blissfulgrin/Documents/UAH_2017_2018/DATOS/PEC2/PEC2/PEC2/Cliente.cpp" $(CXXFLAGS) $(ObjectSwitch)$(IntermediateDirectory)/Cliente.cpp$(ObjectSuffix) $(IncludePath)
$(IntermediateDirectory)/Cliente.cpp$(DependSuffix): Cliente.cpp
	@$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) -MG -MP -MT$(IntermediateDirectory)/Cliente.cpp$(ObjectSuffix) -MF$(IntermediateDirectory)/Cliente.cpp$(DependSuffix) -MM Cliente.cpp

$(IntermediateDirectory)/Cliente.cpp$(PreprocessSuffix): Cliente.cpp
	$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) $(PreprocessOnlySwitch) $(OutputSwitch) $(IntermediateDirectory)/Cliente.cpp$(PreprocessSuffix) Cliente.cpp

$(IntermediateDirectory)/Colores.cpp$(ObjectSuffix): Colores.cpp $(IntermediateDirectory)/Colores.cpp$(DependSuffix)
	$(CXX) $(IncludePCH) $(SourceSwitch) "/Users/mr.blissfulgrin/Documents/UAH_2017_2018/DATOS/PEC2/PEC2/PEC2/Colores.cpp" $(CXXFLAGS) $(ObjectSwitch)$(IntermediateDirectory)/Colores.cpp$(ObjectSuffix) $(IncludePath)
$(IntermediateDirectory)/Colores.cpp$(DependSuffix): Colores.cpp
	@$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) -MG -MP -MT$(IntermediateDirectory)/Colores.cpp$(ObjectSuffix) -MF$(IntermediateDirectory)/Colores.cpp$(DependSuffix) -MM Colores.cpp

$(IntermediateDirectory)/Colores.cpp$(PreprocessSuffix): Colores.cpp
	$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) $(PreprocessOnlySwitch) $(OutputSwitch) $(IntermediateDirectory)/Colores.cpp$(PreprocessSuffix) Colores.cpp

$(IntermediateDirectory)/NodoCola.cpp$(ObjectSuffix): NodoCola.cpp $(IntermediateDirectory)/NodoCola.cpp$(DependSuffix)
	$(CXX) $(IncludePCH) $(SourceSwitch) "/Users/mr.blissfulgrin/Documents/UAH_2017_2018/DATOS/PEC2/PEC2/PEC2/NodoCola.cpp" $(CXXFLAGS) $(ObjectSwitch)$(IntermediateDirectory)/NodoCola.cpp$(ObjectSuffix) $(IncludePath)
$(IntermediateDirectory)/NodoCola.cpp$(DependSuffix): NodoCola.cpp
	@$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) -MG -MP -MT$(IntermediateDirectory)/NodoCola.cpp$(ObjectSuffix) -MF$(IntermediateDirectory)/NodoCola.cpp$(DependSuffix) -MM NodoCola.cpp

$(IntermediateDirectory)/NodoCola.cpp$(PreprocessSuffix): NodoCola.cpp
	$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) $(PreprocessOnlySwitch) $(OutputSwitch) $(IntermediateDirectory)/NodoCola.cpp$(PreprocessSuffix) NodoCola.cpp

$(IntermediateDirectory)/Lista.cpp$(ObjectSuffix): Lista.cpp $(IntermediateDirectory)/Lista.cpp$(DependSuffix)
	$(CXX) $(IncludePCH) $(SourceSwitch) "/Users/mr.blissfulgrin/Documents/UAH_2017_2018/DATOS/PEC2/PEC2/PEC2/Lista.cpp" $(CXXFLAGS) $(ObjectSwitch)$(IntermediateDirectory)/Lista.cpp$(ObjectSuffix) $(IncludePath)
$(IntermediateDirectory)/Lista.cpp$(DependSuffix): Lista.cpp
	@$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) -MG -MP -MT$(IntermediateDirectory)/Lista.cpp$(ObjectSuffix) -MF$(IntermediateDirectory)/Lista.cpp$(DependSuffix) -MM Lista.cpp

$(IntermediateDirectory)/Lista.cpp$(PreprocessSuffix): Lista.cpp
	$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) $(PreprocessOnlySwitch) $(OutputSwitch) $(IntermediateDirectory)/Lista.cpp$(PreprocessSuffix) Lista.cpp

$(IntermediateDirectory)/main.cpp$(ObjectSuffix): main.cpp $(IntermediateDirectory)/main.cpp$(DependSuffix)
	$(CXX) $(IncludePCH) $(SourceSwitch) "/Users/mr.blissfulgrin/Documents/UAH_2017_2018/DATOS/PEC2/PEC2/PEC2/main.cpp" $(CXXFLAGS) $(ObjectSwitch)$(IntermediateDirectory)/main.cpp$(ObjectSuffix) $(IncludePath)
$(IntermediateDirectory)/main.cpp$(DependSuffix): main.cpp
	@$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) -MG -MP -MT$(IntermediateDirectory)/main.cpp$(ObjectSuffix) -MF$(IntermediateDirectory)/main.cpp$(DependSuffix) -MM main.cpp

$(IntermediateDirectory)/main.cpp$(PreprocessSuffix): main.cpp
	$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) $(PreprocessOnlySwitch) $(OutputSwitch) $(IntermediateDirectory)/main.cpp$(PreprocessSuffix) main.cpp

$(IntermediateDirectory)/Arbol.cpp$(ObjectSuffix): Arbol.cpp $(IntermediateDirectory)/Arbol.cpp$(DependSuffix)
	$(CXX) $(IncludePCH) $(SourceSwitch) "/Users/mr.blissfulgrin/Documents/UAH_2017_2018/DATOS/PEC2/PEC2/PEC2/Arbol.cpp" $(CXXFLAGS) $(ObjectSwitch)$(IntermediateDirectory)/Arbol.cpp$(ObjectSuffix) $(IncludePath)
$(IntermediateDirectory)/Arbol.cpp$(DependSuffix): Arbol.cpp
	@$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) -MG -MP -MT$(IntermediateDirectory)/Arbol.cpp$(ObjectSuffix) -MF$(IntermediateDirectory)/Arbol.cpp$(DependSuffix) -MM Arbol.cpp

$(IntermediateDirectory)/Arbol.cpp$(PreprocessSuffix): Arbol.cpp
	$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) $(PreprocessOnlySwitch) $(OutputSwitch) $(IntermediateDirectory)/Arbol.cpp$(PreprocessSuffix) Arbol.cpp


-include $(IntermediateDirectory)/*$(DependSuffix)
##
## Clean
##
clean:
	$(RM) -r ./Debug/


