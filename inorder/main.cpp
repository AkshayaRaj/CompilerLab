/* 
 * File:   main.cpp
 * Author: AkshayaRaj
 *
 * Created on September 14, 2014, 11:53 PM
 */

#include <cstdlib>
#include<stdio.h>

struct node{
    int data;
    node* left;
    node* right;
};
typedef struct node node;

struct snode{
    node* tNode;   //data of the linked list
    snode* next;   //next link of the linked list 
};

typedef struct snode snode;

void push(snode** sp,node* toAdd );
node* pop(snode** sp);
bool isEmpty(snode* top);


node* getNode(int data){
    node* newNode=(node*)malloc(sizeof(node));
    newNode->left=NULL;
    newNode->right=NULL;
    newNode->data=data;
    return newNode;
}

int height(node* root){
    if(root==NULL)
        return 0;
    else{
        int lHeight=height(root->left);
        int rHeight=height(root->right);     
    
    if(lHeight>rHeight)
        return lHeight+1;
    else 
        return rHeight+1;
    }
}

void inOrder(node* root){
    snode* s=NULL;
    node* current=root;
    while(current!=NULL){
        push(&s,current);
        current=current->left;
    }
    if(isEmpty())
}



using namespace std;

/*
 * 
 */
int main(int argc, char** argv) {
    node* root=getNode(1);
    root->left=getNode(2);
    root->right=getNode(3);
    printf("height: %d\n",height(root));
    
    
    //works
    
    return 0;
}

void push(snode** sp,node* toAdd){
    //allocate snode
    snode* new_snode=(snode*)malloc(sizeof(snode));
    if(new_snode==NULL){
        printf("stack Overflow!! \n ");
        exit(0);
    }
    new_snode->tNode=toAdd;
    new_snode->next=*sp;
    
    *sp=new_snode;
    
            
}

node* pop(snode** sp){
    if(*sp==NULL){
        printf("underflow!!");
        exit(0);
    }
    
    snode* popped= *sp;
    node* tnode=popped->tNode;
    *sp=popped->next;
    free (popped);
    return tnode;
    
}

bool isEmpty(snode* top){
    return top==NULL?1:0;
}





