"use client";
import { Input } from "@/components/ui/input";
import {
  Table,
  TableBody,
  TableCaption,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";
import {
  Form,
  FormControl,
  FormDescription,
  FormField,
  FormItem,
  FormLabel,
  FormMessage,
} from "@/components/ui/form";
import { Controller, SubmitHandler, useForm } from "react-hook-form";
import { getSimilar } from "@/http/Phonetics";
import { useEffect, useState } from "react";
import { IPhoneticResponse } from "@/interfaces/IPhonetic";
import { Button } from "@/components/ui/button";

type Inputs = {
  name: string;
};

export default function Home() {
  const [list, setList] = useState<IPhoneticResponse[]>([]);

  const {
    control,
    handleSubmit,
    watch,
    formState: { errors },
  } = useForm<Inputs>();

  const nameValue = watch("name");

  const onSubmit = async (data: Inputs) => {
    console.log(data);
    try {
      const res = await getSimilar(data);
      setList(res);
    } catch (error) {
      console.error("Error fetching similar phonetics:", error);
    }
  };

  useEffect(() => {
    onSubmit({ name: nameValue });
  }, [nameValue]);

  return (
    <div className="flex items-center justify-center h-screen flex-col mx-100 gap-10">
      <form onSubmit={handleSubmit(onSubmit)} className="flex flex-row gap-4">
        <Controller
          name="name"
          control={control}
          render={({ field }) => <Input {...field} />}
        />

        {/* <Button type="submit">Enviar</Button> */}
      </form>

      <Table>
        <TableCaption>Resultados da sua pesquisa</TableCaption>
        <TableHeader>
          <TableRow>
            <TableHead className="w-[100px]">Id</TableHead>
            <TableHead>Name</TableHead>
            <TableHead className="text-right">Hash</TableHead>
          </TableRow>
        </TableHeader>
        <TableBody>
          {list.map((item) => (
            <TableRow>
              <TableCell className="font-medium">{item.id}</TableCell>
              <TableCell>{item.name}</TableCell>
              <TableCell className="text-right">{item.code}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </div>
  );
}
