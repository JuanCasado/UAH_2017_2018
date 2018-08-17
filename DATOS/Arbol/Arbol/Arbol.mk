##
## Auto Generated makefile by CodeLite IDE
## any manual changes will be erased      
##
## Debug
ProjectName            :=Arbol
ConfigurationName      :=Debug
WorkspacePath          :=/Users/mr.blissfulgrin/Documents/UAH_2017_2018/DATOS/Arbol
ProjectPath            :=/Users/mr.blissfulgrin/Documents/UAH_2017_2018/DATOS/Arbol/Arbol
IntermediateDirectory  :=./Debug
OutDir                 := $(IntermediateDirectory)
CurrentFileName        :=
CurrentFilePath        :=
CurrentFileFullPath    :=
User                   :=Juan Casado Ballesteros
Date                   :=05/12/2017
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
ObjectsFileList        :="Arbol.txt"
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
Objects0=$(IntermediateDirectory)/main.cpp$(ObjectSuffix) $(IntermediateDirectory)/NodoArbol.cpp$(ObjectSuffix) $(IntermediateDirectory)/Arbol.cpp$(ObjectSuffix) $(IntermediateDirectory)/Gestor.cpp$(ObjectSuffix) $(IntermediateDirectory)/Trunck.cpp$(ObjectSuffix) 



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
$(IntermediateDirectory)/main.cpp$(ObjectSuffix): main.cpp $(IntermediateDirectory)/main.cpp$(DependSuffix)
	$(CXX) $(IncludePCH) $(SourceSwitch) "/Users/mr.blissfulgrin/Documents/UAH_2017_2018/DATOS/Arbol/Arbol/main.cpp" $(CXXFLAGS) $(ObjectSwitch)$(IntermediateDirectory)/main.cpp$(ObjectSuffix) $(IncludePath)
$(IntermediateDirectory)/main.cpp$(DependSuffix): main.cpp
	@$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) -MG -MP -MT$(IntermediateDirectory)/main.cpp$(ObjectSuffix) -MF$(IntermediateDirectory)/main.cpp$(DependSuffix) -MM main.cpp

$(IntermediateDirectory)/main.cpp$(PreprocessSuffix): main.cpp
	$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) $(PreprocessOnlySwitch) $(OutputSwitch) $(IntermediateDirectory)/main.cpp$(PreprocessSuffix) main.cpp

$(IntermediateDirectory)/NodoArbol.cpp$(ObjectSuffix): NodoArbol.cpp $(IntermediateDirectory)/NodoArbol.cpp$(DependSuffix)
	$(CXX) $(IncludePCH) $(SourceSwitch) "/Users/mr.blissfulgrin/Documents/UAH_2017_2018/DATOS/Arbol/Arbol/NodoArbol.cpp" $(CXXFLAGS) $(ObjectSwitch)$(IntermediateDirectory)/NodoArbol.cpp$(ObjectSuffix) $(IncludePath)
$(IntermediateDirectory)/NodoArbol.cpp$(DependSuffix): NodoArbol.cpp
	@$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) -MG -MP -MT$(IntermediateDirectory)/NodoArbol.cpp$(ObjectSuffix) -MF$(IntermediateDirectory)/NodoArbol.cpp$(DependSuffix) -MM NodoArbol.cpp

$(IntermediateDirectory)/NodoArbol.cpp$(PreprocessSuffix): NodoArbol.cpp
	$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) $(PreprocessOnlySwitch) $(OutputSwitch) $(IntermediateDirectory)/NodoArbol.cpp$(PreprocessSuffix) NodoArbol.cpp

$(IntermediateDirectory)/Arbol.cpp$(ObjectSuffix): Arbol.cpp $(IntermediateDirectory)/Arbol.cpp$(DependSuffix)
	$(CXX) $(IncludePCH) $(SourceSwitch) "/Users/mr.blissfulgrin/Documents/UAH_2017_2018/DATOS/Arbol/Arbol/Arbol.cpp" $(CXXFLAGS) $(ObjectSwitch)$(IntermediateDirectory)/Arbol.cpp$(ObjectSuffix) $(IncludePath)
$(IntermediateDirectory)/Arbol.cpp$(DependSuffix): Arbol.cpp
	@$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) -MG -MP -MT$(IntermediateDirectory)/Arbol.cpp$(ObjectSuffix) -MF$(IntermediateDirectory)/Arbol.cpp$(DependSuffix) -MM Arbol.cpp

$(IntermediateDirectory)/Arbol.cpp$(PreprocessSuffix): Arbol.cpp
	$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) $(PreprocessOnlySwitch) $(OutputSwitch) $(IntermediateDirectory)/Arbol.cpp$(PreprocessSuffix) Arbol.cpp

$(IntermediateDirectory)/Gestor.cpp$(ObjectSuffix): Gestor.cpp $(IntermediateDirectory)/Gestor.cpp$(DependSuffix)
	$(CXX) $(IncludePCH) $(SourceSwitch) "/Users/mr.blissfulgrin/Documents/UAH_2017_2018/DATOS/Arbol/Arbol/Gestor.cpp" $(CXXFLAGS) $(ObjectSwitch)$(IntermediateDirectory)/Gestor.cpp$(ObjectSuffix) $(IncludePath)
$(IntermediateDirectory)/Gestor.cpp$(DependSuffix): Gestor.cpp
	@$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) -MG -MP -MT$(IntermediateDirectory)/Gestor.cpp$(ObjectSuffix) -MF$(IntermediateDirectory)/Gestor.cpp$(DependSuffix) -MM Gestor.cpp

$(IntermediateDirectory)/Gestor.cpp$(PreprocessSuffix): Gestor.cpp
	$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) $(PreprocessOnlySwitch) $(OutputSwitch) $(IntermediateDirectory)/Gestor.cpp$(PreprocessSuffix) Gestor.cpp

$(IntermediateDirectory)/Trunck.cpp$(ObjectSuffix): Trunck.cpp $(IntermediateDirectory)/Trunck.cpp$(DependSuffix)
	$(CXX) $(IncludePCH) $(SourceSwitch) "/Users/mr.blissfulgrin/Documents/UAH_2017_2018/DATOS/Arbol/Arbol/Trunck.cpp" $(CXXFLAGS) $(ObjectSwitch)$(IntermediateDirectory)/Trunck.cpp$(ObjectSuffix) $(IncludePath)
$(IntermediateDirectory)/Trunck.cpp$(DependSuffix): Trunck.cpp
	@$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) -MG -MP -MT$(IntermediateDirectory)/Trunck.cpp$(ObjectSuffix) -MF$(IntermediateDirectory)/Trunck.cpp$(DependSuffix) -MM Trunck.cpp

$(IntermediateDirectory)/Trunck.cpp$(PreprocessSuffix): Trunck.cpp
	$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) $(PreprocessOnlySwitch) $(OutputSwitch) $(IntermediateDirectory)/Trunck.cpp$(PreprocessSuffix) Trunck.cpp


-include $(IntermediateDirectory)/*$(DependSuffix)
##
## Clean
##
clean:
	$(RM) -r ./Debug/


